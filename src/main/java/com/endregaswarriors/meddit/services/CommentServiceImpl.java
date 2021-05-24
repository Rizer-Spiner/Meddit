package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.MedditComment;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.api.*;
import com.endregaswarriors.meddit.models.database.Comment;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.SubComment;
import com.endregaswarriors.meddit.models.database.Thread;
import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import com.endregaswarriors.meddit.repositories.internal.CommentRepository;
import com.endregaswarriors.meddit.repositories.internal.SubCommmentRepository;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
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
    SubredditMembersRepository membersRepository;

    public CommentServiceImpl(CommentRepository commentRepository
            , SubredditMembersRepository subredditMembersRepository
            , SubCommmentRepository subCommmentRepository
                              ) {
        this.commentRepository = commentRepository;
        this.membersRepository = subredditMembersRepository;
        this.subCommmentRepository = subCommmentRepository;
    }

    @Override
    public CompletableFuture<Response<CommentSection>> getCommentsForThread(GetComments getComments) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<List<Comment>> commentListOptional = commentRepository.findBySubredditId(
                    getComments.getThreadId(), getComments.getPage());

            if (commentListOptional.isEmpty())
                return new Response<>(Status.NO_CONTENT);
            else {
                CommentSection commentSection = CommentSection.builder().build();
                commentSection.setThreadID(getComments.getThreadId());
                List<Comment> comments = commentListOptional.get();
                List<CompletableFuture<MedditComment>> futures = new ArrayList<>();

                for (Comment ct : comments) {
                    CompletableFuture<MedditComment> cCF = CompletableFuture.supplyAsync(() -> {
                        Optional<Long> likes = commentRepository.getLikesByCommentId(ct.getComment_id());
                        Optional<Boolean> likedByUser = commentRepository.getLikeForUserByCommentId(
                                getComments.getUserId(), ct.getComment_id());
                        Optional<Long> commentParent = commentRepository.getParentCommentId(ct.getComment_id());

                        return MedditComment.builder()
                                .commentId(ct.getComment_id())
                                .postedBy(ct.getUser())
                                .content(ct.getContent())
                                .upvoteCounter(likes.isEmpty() ? 0 : likes.get())
                                .upvotedByUser(likedByUser.isPresent())
                                .parentCommentId(commentParent.orElse(-1L))
                                .postDate(ct.getPostDate())
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
            // TODO: 5/24/2021 ASK Justinas if i am able to save if the builders are not full args 
            Comment newCt = commentRepository.save(Comment.builder()
                    .content(addComment.getContent())
                    .postDate(LocalDateTime.now())
                    .thread(Thread.builder().thread_id(addComment.getThreadId()).build())
                    .user(MedditUser.builder().user_id(addComment.getUserId()).build())
                    .build());

            if (addComment.getPreviousCommentId() > -1L)
            {
                subCommmentRepository.save(SubComment.builder()
                        .comment_id(newCt.getComment_id())
                        .parent_id(addComment.getPreviousCommentId()).build());
            }

            return new Response<>(Status.SUCCESS, MedditComment.builder()
                    .parentCommentId(addComment.getPreviousCommentId())
                    .commentId(newCt.getComment_id())
                    .upvoteCounter(0L)
                    .upvotedByUser(false)
                    .postedBy(newCt.getUser())
                    .content(newCt.getContent())
                    .postDate(newCt.getPostDate())
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
                commentRepository.upvoteComment(voteComment.getCommentId(), voteComment.getUserId());
            else commentRepository.downVoteComment(voteComment.getCommentId(), voteComment.getUserId());
            return new Response<>(Status.SUCCESS);

        });
    }
}