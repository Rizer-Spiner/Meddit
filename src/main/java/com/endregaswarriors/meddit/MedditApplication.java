package com.endregaswarriors.meddit;


import com.endregaswarriors.meddit.models.MovieDetails;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import com.endregaswarriors.meddit.models.PersonDetails;
import com.endregaswarriors.meddit.repositories.external.*;

import com.google.gson.*;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@SpringBootApplication
public class MedditApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedditApplication.class, args);



//        TMDBContext context = new TMDBContext();
//
//        String pathParameters = String.format("%s/%s", ApiConstants.MOVIES, "550");
//        String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);
//
//        ResponseEntity<MovieDetails> movieDetails = context.get(pathParameters, queryParameters, CustomExtractorFactory.getMovieDetailsExtractor());
//
//        if (movieDetails.getStatusCode().is2xxSuccessful())
//        {
//            MovieDetails movieDetails1 = movieDetails.getBody();
//            System.out.println(movieDetails1.toString());
//        }
//
//        System.out.println("------AND------");
//
//        String pathParam = String.format("%s/%s", ApiConstants.SEARCH, ApiConstants.MOVIES);
//        String queryParam = String.format("%s=%s&%s=%s&%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY, ApiConstants.QUERY, "room", ApiConstants.PAGE, 1);
//
//        ResponseEntity<List<MovieSearchResult>> movieDetailsList = context.getMultiple(pathParam, queryParam, CustomExtractorFactory.getMovieSearchListExtractor());
//
//        if (movieDetailsList.getStatusCode().is2xxSuccessful())
//        {
//            List<MovieSearchResult> detailsList = movieDetailsList.getBody();
//            for (MovieSearchResult m : detailsList) {
//                System.out.println(m.toString());
//            }
//        }
//

        ApiContext apiContext = new TMDBContext();
        MovieRepository movieRepository = new MovieRepositoryImpl(apiContext);

        CompletableFuture<ResponseEntity<MovieDetails>> movie = movieRepository.getMovieDetailsById(550);
        CompletableFuture<ResponseEntity<List<PersonDetails>>> cast = movieRepository.getMovieCast(550);


        try {
            ResponseEntity<MovieDetails> movieResponse = movie.get();
            if (movieResponse.getStatusCode().is2xxSuccessful())
            {
                System.out.println(Objects.requireNonNull(movieResponse.getBody()).toString());
            }
            else {
                System.out.println(movieResponse.getStatusCode().toString());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("AND");
        System.out.println();

        try {
            ResponseEntity<List<PersonDetails>> castResponse = cast.get();

            if (castResponse.getStatusCode().is2xxSuccessful())
            {
                for (PersonDetails p: Objects.requireNonNull(castResponse.getBody())) {
                    System.out.println(p.toString());
                }
            }
            else {
                System.out.println(castResponse.getStatusCode().toString());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
