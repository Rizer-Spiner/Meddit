package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.Movie;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import com.endregaswarriors.meddit.models.Response;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MovieRedditService {

    CompletableFuture<List<MovieSearchResult>> searchForMovie(String keyword);
    CompletableFuture<Response<Movie>> getMovieDetails(Integer IMDB_id);

}
