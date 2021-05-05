package com.endregaswarriors.meddit.repositories.external;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.lang.Nullable;
import org.springframework.web.client.ResponseExtractor;
import retrofit2.http.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ApiRepository<TType> {

    @GET("/{category}/{id}")
    @Headers("accept: application/json")
    public CompletableFuture<List<TType>> getMultiple(@Path("category") String queryCategory, @Path("id") @Nullable String id, String queryParameters);

    @GET("/{category}/{id}")
    @Headers("accept: application/json")
    public CompletableFuture<TType> get(@Path("category") String queryCategory, @Path("id") @Nullable String id, String queryParameters);

    @GET("/search/{category}")
    @Headers("accept: application/json")
    public CompletableFuture<TType> search(@Path("category") String queryCategory, String queryParameters);

    interface CustomExtractor<TType> {
        TType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;
    }
}
