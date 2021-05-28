package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.*;
import com.endregaswarriors.meddit.models.database.Subreddit;
import com.endregaswarriors.meddit.repositories.external.MovieRepositoryImpl;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import com.endregaswarriors.meddit.repositories.internal.SubredditRepository;
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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class MovieRedditServiceImplTest {

    @Mock
    MovieRepositoryImpl movieRepository;
    @Mock
    SubredditMembersRepository membersRepository;
    @Mock
    SubredditRepository subredditRepository;

    MovieRedditService movieRedditService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        movieRedditService = new MovieRedditServiceImpl(movieRepository, membersRepository, subredditRepository);
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
        MovieSearchResult foundMovie = new MovieSearchResult(550, "Shrek",
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

    @Test
    public void getMovieDetails_allFuturesDoNotRespond() throws ExecutionException, InterruptedException {
        Mockito.when(movieRepository.getMovieDetailsById(anyLong()))
                .thenReturn(new CompletableFuture<>());
        Mockito.when(movieRepository.getMovieImages(anyLong()))
                .thenReturn(new CompletableFuture<>());
        Mockito.when(movieRepository.getMovieVideos(anyLong()))
                .thenReturn(new CompletableFuture<>());
        Mockito.when(movieRepository.getMovieCast(anyLong()))
                .thenReturn(new CompletableFuture<>());

        CompletableFuture<Response<Movie>> futureMovieResponse = movieRedditService.getMovieDetails(550);
        Response<Movie> movieResponse = futureMovieResponse.get();

        assertEquals(Status.INTERNAL_ERROR, movieResponse.getStatus());
    }

    @Test
    public void getMovieDetails_oneFuturesDidNotRespond() throws ExecutionException, InterruptedException {
        Mockito.when(movieRepository.getMovieDetailsById(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(new MovieDetails(), HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieImages(anyLong()))
                .thenReturn(new CompletableFuture<>());
        Mockito.when(movieRepository.getMovieVideos(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieCast(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK)));

        CompletableFuture<Response<Movie>> futureMovieResponse = movieRedditService.getMovieDetails(550);
        Response<Movie> movieResponse = futureMovieResponse.get();

        assertEquals(Status.INTERNAL_ERROR, movieResponse.getStatus());
    }

    @Test
    public void getMovieDetails_movieRepositoryReturnFails() throws ExecutionException, InterruptedException {
        MovieDetails movieDetails = new MovieDetails(550, "tt0168495", "asdasd", "asdasd",
                "/asdasd.jpg", LocalDate.now(), 125, "released", 6312,8.8);
        List<MovieVideo> movieVideos = new ArrayList<>();
        movieVideos.add(new MovieVideo("key", "name", "something.com"));
        List<PersonDetails> movieCast = new ArrayList<>();
        movieCast.add(new PersonDetails(15, "Writer", "name", 75, "Man",
                "asd.jpg"));

        Mockito.when(movieRepository.getMovieDetailsById(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieDetails, HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieImages(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST)));
        Mockito.when(movieRepository.getMovieVideos(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieVideos, HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieCast(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieCast, HttpStatus.OK)));

        CompletableFuture<Response<Movie>> futureMovieResponse = movieRedditService.getMovieDetails(550);
        Response<Movie> movieResponse = futureMovieResponse.get();

        assertEquals(Status.NOT_FOUND, movieResponse.getStatus());
    }

    @Test
    public void getMovieDetails_movieDetailsDoNotContainIMDB_id() throws ExecutionException, InterruptedException {
        MovieDetails movieDetails = new MovieDetails(550, "null", "asdasd", "asdasd",
                "/asdasd.jpg", LocalDate.now(), 125, "released", 432,8.8);
        List<MovieImage> movieImages = new ArrayList<>();
        movieImages.add(new MovieImage("/dasd", 300, 300, "EN"));
        movieImages.add(new MovieImage("path", 1080, 720, "FR"));
        List<MovieVideo> movieVideos = new ArrayList<>();
        movieVideos.add(new MovieVideo("key", "name", "something.com"));
        List<PersonDetails> movieCast = new ArrayList<>();
        movieCast.add(new PersonDetails(15, "Writer", "name", 75, "Man",
                "asd.jpg"));

        Mockito.when(movieRepository.getMovieDetailsById(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieDetails, HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieImages(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieImages, HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieVideos(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieVideos, HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieCast(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieCast, HttpStatus.OK)));

        CompletableFuture<Response<Movie>> futureMovieResponse = movieRedditService.getMovieDetails(550);
        Response<Movie> movieResponse = futureMovieResponse.get();

        assertEquals(Status.NOT_FOUND, movieResponse.getStatus());
    }

    @Test
    public void getMovieDetails_successfullyGetMovieDetails() throws ExecutionException, InterruptedException {
        MovieDetails movieDetails = new MovieDetails(550, "tt0168495", "asdasd", "asdasd",
                "/asdasd.jpg", LocalDate.now(), 125, "released", 4232,8.8);
        List<MovieImage> movieImages = new ArrayList<>();
        movieImages.add(new MovieImage("/dasd", 300, 300, "EN"));
        movieImages.add(new MovieImage("path", 1080, 720, "FR"));
        List<MovieVideo> movieVideos = new ArrayList<>();
        movieVideos.add(new MovieVideo("key", "name", "something.com"));
        List<PersonDetails> movieCast = new ArrayList<>();
        movieCast.add(new PersonDetails(15, "Writer", "name", 75, "Man",
                "asd.jpg"));
        Optional<Subreddit> optionalSubreddit = Optional.of(Subreddit.builder()
                .subreddit_id(1)
                .movie_id(168495)
                .created(LocalDate.now())
                .build());

        Mockito.when(movieRepository.getMovieDetailsById(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieDetails, HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieImages(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieImages, HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieVideos(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieVideos, HttpStatus.OK)));
        Mockito.when(movieRepository.getMovieCast(anyLong()))
                .thenReturn(CompletableFuture.supplyAsync(() ->
                        new ResponseEntity<>(movieCast, HttpStatus.OK)));
        Mockito.when(subredditRepository.findSubredditByMovie_id(anyInt()))
                .thenReturn(optionalSubreddit);

        CompletableFuture<Response<Movie>> futureMovieResponse = movieRedditService.getMovieDetails(550);
        Response<Movie> movieResponse = futureMovieResponse.get();

        assertEquals(Status.SUCCESS, movieResponse.getStatus());
    }










}