package com.endregaswarriors.meddit.repositories.external;


import org.springframework.stereotype.Component;


@Component
public class MovieRepository {

    public ApiContext apiContext;

    public MovieRepository(ApiContext apiContext) {
        this.apiContext = apiContext;
    }



}
