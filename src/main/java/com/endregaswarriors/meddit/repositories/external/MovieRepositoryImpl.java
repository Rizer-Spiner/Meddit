package com.endregaswarriors.meddit.repositories.external;


import com.endregaswarriors.meddit.models.*;
import org.springframework.http.RequestEntity;
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



    @Override
    public CompletableFuture<ResponseEntity<MovieDetails>> getMovieDetailsById(long id) {
        String pathParameters = String.format("%s/%s", ApiConstants.MOVIES, ""+id);
        String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);

        return CompletableFuture.supplyAsync(() ->
                apiContext.get(pathParameters, queryParameters, CustomExtractorFactory.getMovieDetailsExtractor()));

    }

    public CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchMovies(String query, Integer page) {
        String pathParameters = String.format("%s/%s", ApiConstants.SEARCH, ApiConstants.MOVIES);
        String queryParameters = String.format("%s=%s&%s=%s&%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY, ApiConstants.QUERY, query, ApiConstants.PAGE, page);

        return CompletableFuture.supplyAsync(() ->
                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieSearchListExtractor()));
    }

    @Override
    public CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchInMovies(String queryType, Integer page) {
        String pathParameters = String.format("%s/%s", ApiConstants.MOVIES, queryType);
        String queryParameters = String.format("%s=%s&%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY, ApiConstants.PAGE, page);

        return CompletableFuture.supplyAsync(() ->
                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieSearchListExtractor()));
    }

    @Override
    public CompletableFuture<ResponseEntity<List<MovieImage>>> getMovieImages(long id) {
        String pathParameters = String.format("%s/%s/%s", ApiConstants.MOVIES, ""+id, ApiConstants.IMAGES);
        String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);


        return CompletableFuture.supplyAsync(() ->
                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieImagesListExtractor()));

    }

    @Override
    public CompletableFuture<ResponseEntity<List<MovieVideo>>> getMovieVideos(long id) {
        String pathParameters = String.format("%s/%s/%s", ApiConstants.MOVIES, ""+id, ApiConstants.VIDEOS);
        String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);

        return CompletableFuture.supplyAsync(() ->
                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieVideoListExtractor()));
    }

    @Override
    public CompletableFuture<ResponseEntity<List<PersonDetails>>> getMovieCast(long id) {
        String pathParameters = String.format("%s/%s/%s", ApiConstants.MOVIES, ""+id, ApiConstants.CREDITS);
        String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);

        return CompletableFuture.supplyAsync(() ->
                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieCastListExtractor()));
    }


}
