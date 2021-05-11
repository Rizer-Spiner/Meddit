package com.endregaswarriors.meddit.repositories.external;

import com.endregaswarriors.meddit.models.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MovieRepository {

    CompletableFuture<ResponseEntity<MovieDetails>> getMovieDetailsById(long id);
    CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchMovies(String query, Integer page);
    CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchInMovies(String queryType, Integer page);
    CompletableFuture<ResponseEntity<List<MovieImage>>> getMovieImages(long id);
    CompletableFuture<ResponseEntity<List<MovieVideo>>> getMovieVideos(long id);
    CompletableFuture<ResponseEntity<List<PersonDetails>>> getMovieCast(long id);
}
