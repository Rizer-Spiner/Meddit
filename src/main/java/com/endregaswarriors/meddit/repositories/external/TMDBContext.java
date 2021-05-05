package com.endregaswarriors.meddit.repositories.external;

import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseExtractor;
import retrofit2.Retrofit;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Component
public class TMDBContext implements ApiRepository{

    Logger logger;

    public TMDBContext(Logger logger)
    {
        this.logger = logger;
    }

    @Override
    public CompletableFuture<List> getMultiple(String queryCategory,String id,String queryParameters) {

        return null;
    }

    @Override
    public CompletableFuture get(String queryCategory,String id,  String queryParameters) {
        return null;
    }

    @Override
    public CompletableFuture search(String queryCategory, String queryParameters) {
        return null;
    }
}
