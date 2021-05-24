package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.*;
import com.endregaswarriors.meddit.models.api.AddThread;
import com.endregaswarriors.meddit.models.api.DeleteThread;
import com.endregaswarriors.meddit.models.api.GetThreads;
import com.endregaswarriors.meddit.models.api.VoteThread;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.Thread;
import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import com.endregaswarriors.meddit.repositories.internal.ThreadRepository;
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
public class ThreadServiceImpl implements ThreadService {

    ThreadRepository threadRepository;
    SubredditMembersRepository membersRepository;

    public ThreadServiceImpl(ThreadRepository threadRepository,
                             SubredditMembersRepository membersRepository) {
        this.threadRepository = threadRepository;
        this.membersRepository = membersRepository;
    }


    @Override
    public CompletableFuture<Response<MedditThread>> addThread(AddThread newThread) {

        return CompletableFuture.supplyAsync(() -> {

            if (!membersRepository.existsById(SubredditMemberPK.builder().
                    subreddit_id(newThread.getSubredditId()).
                    user_id(newThread.getPostedByUserId()).
                    build()))
                return new Response<>(Status.NOT_ALLOWED);
            Thread newTd = threadRepository.save(Thread.builder()
                    .subreddit_id(newThread.getSubredditId())
                    .user(MedditUser.builder().user_id(newThread.getPostedByUserId()).build())
                    .title(newThread.getThreadTitle())
                    .content(newThread.getThreadContent())
                    .postdate(LocalDateTime.now()).build());

            return new Response<>(Status.SUCCESS, MedditThread.builder()
                    .threadId(newTd.getThread_id())
                    .threadTitle(newTd.getTitle())
                    .threadContent(newTd.getContent())
                    .postDate(newTd.getPostdate())
                    .postedBy(newTd.getUser())
                    .upvoteCounter(0L)
                    .upvotedByUser(false)
                    .build());
        });

    }

    @Override
    public CompletableFuture<Response<Void>> deleteThread(DeleteThread deleteThread) {

        return CompletableFuture.supplyAsync(() -> {
            if (!membersRepository.existsById(SubredditMemberPK.builder().
                    subreddit_id(deleteThread.getSubredditId()).
                    user_id(deleteThread.getUserId()).
                    build()))
                return new Response(Status.NOT_ALLOWED);
            threadRepository.deleteById(deleteThread.getThreadId());
            return new Response<>(Status.SUCCESS);
        });


    }


    @Override
    public CompletableFuture<Response<List<MedditThread>>> getSubredditThreads(GetThreads getThreadsModel) {

        return CompletableFuture.supplyAsync(() -> {
            Optional<List<Thread>> threadsListOptional = threadRepository.findBySubredditId(getThreadsModel.getSubredditId(), getThreadsModel.getPage());

            if (threadsListOptional.isEmpty())
                return new Response<>(Status.NO_CONTENT, new ArrayList<>());
            else {
                List<MedditThread> medditThreads = new ArrayList<>();
                List<Thread> threads = threadsListOptional.get();
                List<CompletableFuture<MedditThread>> futures = new ArrayList<>();

                for (Thread td : threads) {
                    CompletableFuture<MedditThread> mCF = CompletableFuture.supplyAsync(() -> {
                        Optional<Long> likes = threadRepository.getLikesByThreadId(td.getThread_id());
                        Optional<Boolean> likedByUser = threadRepository.getLikeForUserByThreadId(getThreadsModel.getUserId(), td.getThread_id());

                        return MedditThread.builder()
                                .threadId(td.getThread_id())
                                .postedBy(td.getUser())
                                .threadTitle(td.getTitle())
                                .threadContent(td.getContent())
                                .upvoteCounter(likes.isEmpty() ? 0 : likes.get())
                                .upvotedByUser(likedByUser.isPresent())
                                .postDate(td.getPostdate())
                                .build();
                    });
                    futures.add(mCF);
                }

                CompletableFuture[] cfs = futures.toArray(new CompletableFuture[futures.size()]);
                CompletableFuture combinedFuture = CompletableFuture.allOf(cfs);

                try {
                    combinedFuture.get(6, TimeUnit.SECONDS);

                    for (CompletableFuture<MedditThread> md : futures) {
                        medditThreads.add(md.get());
                    }

                    return new Response<>(Status.SUCCESS, medditThreads);

                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    return new Response<>(Status.INTERNAL_ERROR);
                }
            }
        });


    }

    @Override
    public CompletableFuture<Response<Void>> upVoteThread(VoteThread upvoteThread) {
        return CompletableFuture.supplyAsync(() -> {
            if (!membersRepository.existsById(SubredditMemberPK.builder().
                    subreddit_id(upvoteThread.getSubredditId()).
                    user_id(upvoteThread.getUserId()).
                    build()))
                return new Response(Status.NOT_ALLOWED);
            if (upvoteThread.getUpvote())
                threadRepository.upvoteThread(upvoteThread.getThreadId(), upvoteThread.getUserId());
            else threadRepository.downVoteThread(upvoteThread.getThreadId(), upvoteThread.getUserId());
            return new Response<>(Status.SUCCESS);

        });

    }
}
