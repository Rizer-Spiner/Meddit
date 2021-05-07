package com.endregaswarriors.meddit.repositories.external;

import com.endregaswarriors.meddit.models.MovieDetails;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MovieRepository {

    CompletableFuture<ResponseEntity<MovieDetails>> getMovieDetailsById(String id);
    CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchMovies(String category, String query, Integer page);
}
