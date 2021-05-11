package com.endregaswarriors.meddit;


import com.endregaswarriors.meddit.models.MovieDetails;
import com.endregaswarriors.meddit.models.MovieImage;
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

        ApiContext apiContext = new TMDBContext();
//        ApiContext apiContext1 = new TMDBContext();
//        ApiContext apiContext2= new TMDBContext();
        MovieRepository movieRepository = new MovieRepositoryImpl(apiContext);
//        MovieRepository movieRepository1 = new MovieRepositoryImpl(apiContext1);
//        MovieRepository movieRepository2 = new MovieRepositoryImpl(apiContext2);

        CompletableFuture<ResponseEntity<MovieDetails>> movie = movieRepository.getMovieDetailsById(550);
        CompletableFuture<ResponseEntity<List<PersonDetails>>> cast = movieRepository.getMovieCast(550);
        CompletableFuture<ResponseEntity<List<MovieImage>>> images = movieRepository.getMovieImages(550);


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
//
        System.out.println();
        System.out.println("AND");
        System.out.println();
//
        try {
            ResponseEntity<List<MovieImage>> imagesResponse = images.get();

            if (imagesResponse.getStatusCode().is2xxSuccessful())
            {
                for (MovieImage p: Objects.requireNonNull(imagesResponse.getBody())) {
                    System.out.println(p.toString());
                }
            }
            else {
                System.out.println(imagesResponse.getStatusCode().toString());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
