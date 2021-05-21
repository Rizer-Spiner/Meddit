package com.endregaswarriors.meddit.repositories.external;

import com.endregaswarriors.meddit.models.MovieDetails;
import com.endregaswarriors.meddit.models.MovieImage;

import com.endregaswarriors.meddit.models.MovieVideo;
import org.junit.Rule;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class MovieRepositoryImplTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TMDBContext mockContext;

    MovieRepository subject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subject = new MovieRepositoryImpl(mockContext);
    }

    @Autowired
    TMDBContext context;

    @Test
    public void performanceTest() {

        MovieRepository movieRepository = new MovieRepositoryImpl(context);
        Runnable runnable = () -> movieRepository.getMovieImages(550);

        System.out.println("Start time: " + LocalDateTime.now());
        Future f = Executors.newFixedThreadPool(15).submit(runnable);
        Future f2 = Executors.newFixedThreadPool(15).submit(runnable);
        Future f3 = Executors.newFixedThreadPool(15).submit(runnable);
        Future f4 = Executors.newFixedThreadPool(15).submit(runnable);
        LocalDateTime start = LocalDateTime.now();
        final LocalDateTime[] finish = new LocalDateTime[1];
        System.out.println("Reached firing f5: " + start);
        Future f5 = Executors.newFixedThreadPool(15).submit(runnable);
        Future f6 = Executors.newFixedThreadPool(15).submit(runnable);
        Future f7 = Executors.newFixedThreadPool(15).submit(runnable);
        Future f8 = Executors.newFixedThreadPool(15).submit(runnable);
        Future f9 = Executors.newFixedThreadPool(15).submit(runnable);
        Future f10 = Executors.newFixedThreadPool(15).submit(runnable);
        System.out.println("Reached firing f10: " + LocalDateTime.now());
        CompletableFuture.runAsync(() -> {
            boolean Done = false;
            while (!Done) {
                if (f5.isDone()) {
                    finish[0] = LocalDateTime.now();
                    System.out.println("F5 finished at: " + finish[0]);
                    Done = true;
                }
            }
        });
        for (int i = 0; i < 1000; i++) {
            Executors.newFixedThreadPool(15).submit(runnable);
        }
        System.out.println("Fired another 1000 request: " + LocalDateTime.now());

        assertTrue(finish[0].getSecond() - start.getSecond() < 1);
    }


    @Test
    public void asyncronizationTest() {
        /// Run this test in main
        MovieRepository movieRepository2 = new MovieRepositoryImpl(new TMDBContext());

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository2.getMovieImages(550).get();
                System.out.println("1: " + r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository2.getMovieImages(550).get();
                System.out.println("2: " + r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository2.getMovieImages(550).get();
                System.out.println("3: " + r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieVideo>> r = movieRepository2.getMovieVideos(550).get();
                System.out.println("4: " + r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository2.getMovieImages(550).get();
                System.out.println("5: " + r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository2.getMovieImages(550).get();
                System.out.println("6: " + r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });


        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieVideo>> r = movieRepository2.getMovieVideos(550).get();
                System.out.println("7: " + r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

    }

    @Test
    public void getMovieDetailsWithWrongPath() {
        subject = new MovieRepositoryImpl(mockContext);
        String goodPathParameters = String.format("%s/%s", ApiConstants.MOVIES, 500);
        String goodQueryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);

        Mockito.when(mockContext.getAsync(any(String.class), any(String.class),
                any(CustomExtractorFactory.getMovieDetailsExtractor().getClass())))
                .thenReturn(CompletableFuture.supplyAsync(() -> new ResponseEntity(null, HttpStatus.BAD_GATEWAY)));

        Mockito.when(mockContext.getAsync(goodPathParameters, goodQueryParameters,
                CustomExtractorFactory.getMovieDetailsExtractor()))
                .thenReturn(CompletableFuture.supplyAsync(() -> new ResponseEntity(new MovieDetails(), HttpStatus.OK)));

        CompletableFuture<ResponseEntity<MovieDetails>> result = subject.getMovieDetailsById(500);
        try {
            assertEquals(HttpStatus.OK, result.get().getStatusCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getMovieDetailsNotFound()
    {
        subject = new MovieRepositoryImpl(mockContext);
        String badPathParameters = String.format("%s/%s", ApiConstants.MOVIES, 7);
        String goodQueryParameters = String.format("%s=%s", ApiConstants.API_KEY, ApiConstants.TMDB_API_KEY);

        Mockito.when(mockContext.getAsync(any(String.class), any(String.class),
                any(CustomExtractorFactory.getMovieDetailsExtractor().getClass())))
                .thenReturn(CompletableFuture.supplyAsync(() -> new ResponseEntity(new MovieDetails(), HttpStatus.OK)));

        Mockito.when(mockContext.getAsync(badPathParameters, goodQueryParameters,
                CustomExtractorFactory.getMovieDetailsExtractor()))
                .thenReturn(CompletableFuture.supplyAsync(() -> new ResponseEntity(null, HttpStatus.NOT_FOUND)));

        CompletableFuture<ResponseEntity<MovieDetails>> result = subject.getMovieDetailsById(7);
        try {
            assertEquals(HttpStatus.NOT_FOUND, result.get().getStatusCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}