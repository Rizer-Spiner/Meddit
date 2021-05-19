package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.Movie;
import com.endregaswarriors.meddit.models.MovieDetails;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.repositories.external.MovieRepository;
import com.endregaswarriors.meddit.repositories.external.MovieRepositoryImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class MovieRedditServiceImpl implements MovieRedditService{

    MovieRepository repository;

    public MovieRedditServiceImpl(MovieRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public CompletableFuture<List<MovieSearchResult>> searchForMovie(String keyword) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<ResponseEntity<List<MovieSearchResult>>> response = repository.searchMovies(keyword, 1);
            try {
                ResponseEntity<List<MovieSearchResult>> responseEntity = response.get(5, TimeUnit.SECONDS);
                if(responseEntity.getStatusCode().is2xxSuccessful())
                    return responseEntity.getBody();
                else
                    return new ArrayList<>();
            } catch (Exception e) {
                return new ArrayList<>();
            }
        });
    }

    @Override
    public CompletableFuture<Response<Movie>> getMovieDetails(Integer IMDB_id) {
        return null;
    }
}
