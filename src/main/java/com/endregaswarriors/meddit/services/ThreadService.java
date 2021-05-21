package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.AddThread;
import com.endregaswarriors.meddit.models.Response;

import java.util.concurrent.CompletableFuture;

public interface ThreadService {

    public CompletableFuture<Response<Void>> addThread(AddThread newThread);

}
