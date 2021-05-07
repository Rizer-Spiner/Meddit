package com.endregaswarriors.meddit;


import com.endregaswarriors.meddit.models.MovieDetails;
import com.endregaswarriors.meddit.repositories.external.ApiConstants;

import com.endregaswarriors.meddit.repositories.external.CustomExtractorFactory;
import com.endregaswarriors.meddit.repositories.external.TMDBContext;
import com.google.gson.*;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;

import java.util.List;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class MedditApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedditApplication.class, args);



        TMDBContext context = new TMDBContext();

        String pathParameters = String.format("%s/%s", ApiConstants.MOVIES, "550");
        String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);

        ResponseEntity<MovieDetails> movieDetails = context.get(pathParameters, queryParameters, CustomExtractorFactory.getMovieDetailsExtractor());

        if (movieDetails.getStatusCode().is2xxSuccessful())
        {
            MovieDetails movieDetails1 = movieDetails.getBody();
            System.out.println(movieDetails1.toString());
        }

        System.out.println("------AND------");

        String pathParam = String.format("%s/%s", ApiConstants.MOVIES, "popular");
        String queryParam = String.format("%s=%s&%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY, "page", "1");

        ResponseEntity<List<MovieDetails>> movieDetailsList = context.getMultiple(pathParam, queryParam, CustomExtractorFactory.getMovieDetailsListExtractor());

        if (movieDetailsList.getStatusCode().is2xxSuccessful())
        {
            List<MovieDetails> detailsList = movieDetailsList.getBody();
            for (MovieDetails m : detailsList) {
                System.out.println(m.toString());
            }
        }


    }

}
