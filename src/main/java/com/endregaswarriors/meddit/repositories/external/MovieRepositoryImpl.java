package com.endregaswarriors.meddit.repositories.external;


import com.endregaswarriors.meddit.models.MovieDetails;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Component
public class MovieRepositoryImpl implements MovieRepository {

    public ApiContext apiContext;

    public MovieRepositoryImpl(ApiContext apiContext) {
        this.apiContext = apiContext;
    }

    public CompletableFuture<ResponseEntity<MovieDetails>> getMovieDetailsById(String id) {
        String pathParameters = String.format("%s/%s", ApiConstants.MOVIES, "550");
        String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);

        return CompletableFuture.supplyAsync(() ->
                apiContext.get(pathParameters, queryParameters, CustomExtractorFactory.getMovieDetailsExtractor()));

    }

    public CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchMovies(String category, String query, Integer page) {
        String pathParameters = String.format("%s/%s", ApiConstants.SEARCH, category);
        String queryParameters = String.format("%s=%s&%s=%s&%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY, ApiConstants.QUERY, query, ApiConstants.PAGE, page);

        return CompletableFuture.supplyAsync(() ->
                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieSearchListExtractor()));
    }

}
