package com.endregaswarriors.meddit.repositories.external;


import com.endregaswarriors.meddit.models.*;
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
        final String pathParameters = String.format("%s/%s", ApiConstants.MOVIES, "" + id);
        final String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);

        return CompletableFuture.supplyAsync(() ->
                apiContext.get(pathParameters, queryParameters, CustomExtractorFactory.getMovieDetailsExtractor()));
//        return apiContext.getAsync(pathParameters, queryParameters, CustomExtractorFactory.getMovieDetailsExtractor());
    }

    public CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchMovies(String query, Integer page) {
        final String pathParameters = String.format("%s/%s", ApiConstants.SEARCH, ApiConstants.MOVIES);
        final String queryParameters = String.format("%s=%s&%s=%s&%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY, ApiConstants.QUERY, query, ApiConstants.PAGE, page);

        return CompletableFuture.supplyAsync(() ->
                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieSearchListExtractor()));

//        return apiContext.getMultipleAsync(pathParameters, queryParameters, CustomExtractorFactory.getMovieSearchListExtractor());
    }

    @Override
    public CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchInMovies(String queryType, Integer page) {
        final String pathParameters = String.format("%s/%s", ApiConstants.MOVIES, queryType);
        final String queryParameters = String.format("%s=%s&%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY, ApiConstants.PAGE, page);
//
        return CompletableFuture.supplyAsync(() ->
                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieSearchListExtractor()));
//        return apiContext.getMultipleAsync(pathParameters, queryParameters, CustomExtractorFactory.getMovieSearchListExtractor());
    }

    @Override
    public CompletableFuture<ResponseEntity<List<MovieImage>>> getMovieImages(long id) {
        final String pathParameters = String.format("%s/%s/%s", ApiConstants.MOVIES, "" + id, ApiConstants.IMAGES);
        final String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);


//        return CompletableFuture.supplyAsync(() ->
//                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieImagesListExtractor()));

        return apiContext.getMultipleAsync(pathParameters, queryParameters, CustomExtractorFactory.getMovieImagesListExtractor());
    }

    @Override
    public CompletableFuture<ResponseEntity<List<MovieVideo>>> getMovieVideos(long id) {
        final String pathParameters = String.format("%s/%s/%s", ApiConstants.MOVIES, "" + id, ApiConstants.VIDEOS);
        final String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);

        return CompletableFuture.supplyAsync(() ->
                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieVideoListExtractor()));
//        return apiContext.getMultipleAsync(pathParameters, queryParameters, CustomExtractorFactory.getMovieVideoListExtractor());
    }

    @Override
    public CompletableFuture<ResponseEntity<List<PersonDetails>>> getMovieCast(long id) {
        final String pathParameters = String.format("%s/%s/%s", ApiConstants.MOVIES, "" + id, ApiConstants.CREDITS);
        final String queryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);
//
//        return CompletableFuture.supplyAsync(() ->
//                apiContext.getMultiple(pathParameters, queryParameters, CustomExtractorFactory.getMovieCastListExtractor()));

        return apiContext.getMultipleAsync(pathParameters, queryParameters, CustomExtractorFactory.getMovieCastListExtractor());
    }


}
