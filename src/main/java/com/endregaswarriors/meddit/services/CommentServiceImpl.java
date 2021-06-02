package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.CommentSection;
import com.endregaswarriors.meddit.models.MedditComment;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.api.*;
import com.endregaswarriors.meddit.models.database.Comment;
import com.endregaswarriors.meddit.models.database.CommentLike;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.SubComment;
import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import com.endregaswarriors.meddit.repositories.internal.CommentLikeRepository;
import com.endregaswarriors.meddit.repositories.internal.CommentRepository;
import com.endregaswarriors.meddit.repositories.internal.SubCommmentRepository;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;
    SubCommmentRepository subCommmentRepository;
    CommentLikeRepository commentLikeRepository;
    SubredditMembersRepository membersRepository;

    public CommentServiceImpl(CommentRepository commentRepository
            , SubredditMembersRepository subredditMembersRepository
            , SubCommmentRepository subCommmentRepository
            , CommentLikeRepository commentLikeRepository
                              ) {
        this.commentRepository = commentRepository;
        this.membersRepository = subredditMembersRepository;
        this.subCommmentRepository = subCommmentRepository;
        this.commentLikeRepository = commentLikeRepository;
    }

    @Override
    public CompletableFuture<Response<CommentSection>> getCommentsForThread(GetComments getComments) {
        return CompletableFuture.supplyAsync(() -> {

            Page<Comment> commentListOptional = commentRepository.findAllByThread_id(
                    getComments.getThreadId(), PageRequest.of(getComments.getPage(),100));

            if (commentListOptional.isEmpty())
                return new Response<>(Status.NO_CONTENT);
            else {
                CommentSection commentSection = CommentSection.builder()
                        .threadID(getComments.getThreadId())
                        .commentList(new ArrayList<>())
                        .build();
                commentSection.setThreadID(getComments.getThreadId());
                List<Comment> comments = commentListOptional.getContent();
                List<CompletableFuture<MedditComment>> futures = new ArrayList<>();

                for (Comment ct : comments) {
                    CompletableFuture<MedditComment> cCF = CompletableFuture.supplyAsync(() -> {
                        Optional<Long> likes = commentLikeRepository.getLikesByCommentId(ct.getComment_id());
                        Optional<CommentLike> likedByUser = commentLikeRepository.getLikeForUserByCommentId(
                                getComments.getUserId(), ct.getComment_id());
                        Optional<Long> commentParent = subCommmentRepository.getParentCommentId(ct.getComment_id());

                        return MedditComment.builder()
                                .commentId(ct.getComment_id())
                                .postedBy(ct.getUser())
                                .content(ct.getContent())
                                .upvoteCounter(likes.isEmpty() ? 0 : likes.get())
                                .upvotedByUser(likedByUser.isPresent())
                                .parentCommentId(commentParent.orElse(-1L))
                                .postDate(ct.getPostdate())
                                .build();
                    });
                    futures.add(cCF);
                }
                CompletableFuture[] cfs = futures.toArray(new CompletableFuture[futures.size()]);
                CompletableFuture combinedFuture = CompletableFuture.allOf(cfs);

                try {
                    combinedFuture.get(6, TimeUnit.SECONDS);

                    for (CompletableFuture<MedditComment> mc : futures) {
                        commentSection.getCommentList().add(mc.get());
                    }

                    return new Response<>(Status.SUCCESS, commentSection);

                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    return new Response<>(Status.INTERNAL_ERROR);
                }
            }
        });
    }

    @Override
    public CompletableFuture<Response<MedditComment>> addComment(AddComment addComment) {
        return CompletableFuture.supplyAsync(() -> {
            if (!membersRepository.existsById(SubredditMemberPK.builder().
                    subreddit_id(addComment.getSubredditId()).
                    user_id(addComment.getUserId()).
                    build()))
                return new Response<>(Status.NOT_ALLOWED);

            Comment newCt = commentRepository.save(Comment.builder()
                    .content(addComment.getContent())
                    .postdate(LocalDateTime.now())
                    .thread_id(addComment.getThreadId())
                    .user(MedditUser.builder().user_id(addComment.getUserId()).build())
                    .build());

            if (addComment.getPreviousCommentId() > -1L)
            {
                subCommmentRepository.save(SubComment.builder()
                        .comment_id(newCt.getComment_id())
                        .parent_id(addComment.getPreviousCommentId()).build());
            }

            return new Response<>(Status.CREATED, MedditComment.builder()
                    .parentCommentId(addComment.getPreviousCommentId())
                    .commentId(newCt.getComment_id())
                    .upvoteCounter(0L)
                    .upvotedByUser(false)
                    .postedBy(newCt.getUser())
                    .content(newCt.getContent())
                    .postDate(newCt.getPostdate())
                    .build());
        });
    }

    @Override
    public CompletableFuture<Response<Void>> deleteComment(DeleteComment deleteComment) {
        return CompletableFuture.supplyAsync(() -> {
            if (!membersRepository.existsById(SubredditMemberPK.builder().
                    subreddit_id(deleteComment.getSubredditId()).
                    user_id(deleteComment.getUserId()).
                    build()))
                return new Response(Status.NOT_ALLOWED);
            commentRepository.deleteById(deleteComment.getCommentId());
            return new Response<>(Status.SUCCESS);
        });
    }

    @Override
    public CompletableFuture<Response<Void>> upvoteComment(VoteComment voteComment) {
        return CompletableFuture.supplyAsync(() -> {
            if (!membersRepository.existsById(SubredditMemberPK.builder().
                    subreddit_id(voteComment.getSubredditId()).
                    user_id(voteComment.getUserId()).
                    build()))
                return new Response(Status.NOT_ALLOWED);
            if (voteComment.getUpvote())
                commentLikeRepository.upvoteComment(voteComment.getCommentId(), voteComment.getUserId());
            else commentLikeRepository.downVoteComment(voteComment.getCommentId(), voteComment.getUserId());
            return new Response<>(Status.SUCCESS);

        });
    }
}
