package com.endregaswarriors.meddit.api;
import com.endregaswarriors.meddit.models.Response;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;



public abstract class ControllerBase {

    protected <TType> CompletableFuture<ResponseEntity<TType>> custom(int status) {
        return custom(status, null);
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> custom(int status, TType body) {
        return CompletableFuture.supplyAsync(()->
        {
            if (body == null)
                return ResponseEntity
                        .status(status)
                        .build();

            return ResponseEntity
                    .status(status)
                    .body(body);
        });
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> success(TType body) {
        return custom(200, body);
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> success() {
        return custom(200);
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> notFound() {
        return custom(404);
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> notFound(TType body) {
        return custom(404, body);
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> forbidden() {
        return custom(403);
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> badRequest() {
        return custom(400);
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> successOrNotFound(TType object) {
        return object == null ? notFound() : success(object);
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> error() {
        return custom(500);
    }

    protected <TType> CompletableFuture<ResponseEntity<TType>> map(Response<TType> response) {
        switch (response.getStatus()) {
            case SUCCESS:
                return custom(200, response.getModel());
            case CREATED:
                return custom(201, response.getModel());
            case NO_CONTENT:
                return custom(204);
            case UNAUTHORIZED:
                return custom(403);
            case NOT_ALLOWED:
                return custom(406);
            case NOT_FOUND:
                return custom(404);
            case CONFLICT:
                return custom(409);
            case NOT_IMPLEMENTED:
                return custom(501);
            default:
                return custom(500);
        }
    }


}
