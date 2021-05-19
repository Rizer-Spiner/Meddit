package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.MovieSearchResult;
import com.endregaswarriors.meddit.repositories.external.MovieRepository;
import com.endregaswarriors.meddit.repositories.external.MovieRepositoryImpl;
import com.endregaswarriors.meddit.repositories.external.TMDBContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

class MovieRedditServiceImplTest {

    @Mock
    MovieRepositoryImpl movieRepository;

    MovieRedditService movieRedditService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        movieRedditService = new MovieRedditServiceImpl(movieRepository);
    }

    @Test
    public void searchForMovie_timeout() throws ExecutionException, InterruptedException {
        //when movie repository does not return a response
        CompletableFuture<List<MovieSearchResult>> futureList = movieRedditService.searchForMovie("Shrek");
        List<MovieSearchResult> receivedList = futureList.get();

        assertEquals(new ArrayList<MovieSearchResult>(), receivedList);
    }

    @Test
    public void searchForMovie_not200() throws ExecutionException, InterruptedException {
        Mockito.when(movieRepository.searchMovies(anyString(), anyInt()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST)));

        CompletableFuture<List<MovieSearchResult>> futureList = movieRedditService.searchForMovie("Shrek");
        List<MovieSearchResult> receivedList = futureList.get();

        assertEquals(new ArrayList<MovieSearchResult>(), receivedList);
    }

    @Test
    public void searchForMovie_success() throws ExecutionException, InterruptedException {
        MovieSearchResult foundMovie = new MovieSearchResult("550", "Shrek",
                "Green man", LocalDate.now(), 9.9, "/smth.jpg", 9000.1);
        List<MovieSearchResult> movieList = new ArrayList<>();
        movieList.add(foundMovie);

        Mockito.when(movieRepository.searchMovies(anyString(), anyInt()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieList, HttpStatus.OK)));

        CompletableFuture<List<MovieSearchResult>> futureList = movieRedditService.searchForMovie("Shrek");
        List<MovieSearchResult> receivedList = futureList.get();

        assertEquals(movieList, receivedList);
    }

}