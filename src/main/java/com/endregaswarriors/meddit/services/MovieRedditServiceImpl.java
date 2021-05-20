package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.Movie;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import com.endregaswarriors.meddit.models.Response;

import com.endregaswarriors.meddit.models.Status;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class MovieRedditServiceImpl implements MovieRedditService{



    @Override
    public CompletableFuture<List<MovieSearchResult>> searchForMovie(String keyword) {

        return CompletableFuture.supplyAsync(()->{
            List<MovieSearchResult> list = new ArrayList<>();
            list.add(new MovieSearchResult(888, "I am not a movie", "Blank", LocalDate.now(), 6.6, "/not telling", 5.5));
            list.add(new MovieSearchResult(889, "I am not a movie2", "Blank2", LocalDate.now(), 6.8, "/not telling2", 6.5));
            return list;
        });
    }

    @Override
    public CompletableFuture<Response<Movie>> getMovieDetails(Integer IMDB_id) {
        return CompletableFuture.supplyAsync(()-> new Response<>(Status.SUCCESS, new Movie(null, null, null, null, null)));
    }
}
