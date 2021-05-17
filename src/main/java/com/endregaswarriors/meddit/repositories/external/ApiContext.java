package com.endregaswarriors.meddit.repositories.external;

import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;



public interface ApiContext {

    <TType> ResponseEntity<List<TType>> getMultiple(String pathParameters, String queryParameters, CustomExtractor<List<TType>> deserializer);
    <TType> ResponseEntity<TType> get(String pathParameters, String queryParameters, CustomExtractor<TType> deserializer);

    interface CustomExtractor<TType> {
        TType deserialize(String string);
    }

}