package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.*;
import com.endregaswarriors.meddit.models.api.AddThread;
import com.endregaswarriors.meddit.models.api.DeleteThread;
import com.endregaswarriors.meddit.models.api.GetThreads;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ThreadService {

    CompletableFuture<Response<MedditThread>> addThread(AddThread newThread);
    CompletableFuture<Response<Void>> deleteThread(DeleteThread deleteThread);
    CompletableFuture<Response<List<MedditThread>>> getSubredditThreads(GetThreads getThreadsModel);
    CompletableFuture<Response<Void>> upVoteThread(VoteThread upvoteThread);
}
