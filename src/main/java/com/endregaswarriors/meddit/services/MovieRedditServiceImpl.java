package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.Movie;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import com.endregaswarriors.meddit.models.Response;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MovieRedditServiceImpl implements MovieRedditService{



    @Override
    public CompletableFuture<List<MovieSearchResult>> searchForMovie(String keyword) {
        return null;
    }

    @Override
    public CompletableFuture<Response<Movie>> getMovieDetails(Integer IMDB_id) {
        return null;
    }
}
