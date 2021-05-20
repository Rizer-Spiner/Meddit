package com.endregaswarriors.meddit.repositories.external;

import com.endregaswarriors.meddit.models.MovieImage;
import com.endregaswarriors.meddit.models.MovieVideo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class MovieRepositoryImplTest {
    @Autowired TMDBContext context;
    @Test
    public void performanceTest()
    {

        MovieRepository movieRepository = new MovieRepositoryImpl(context);
        Runnable runnable = () -> movieRepository.getMovieImages(550);

        System.out.println("Start time: " + LocalDateTime.now());
        Future f = Executors.newFixedThreadPool(15).submit(runnable);
        Future f2 = Executors.newFixedThreadPool(15).submit(runnable);
        Future f3 = Executors.newFixedThreadPool(15).submit(runnable);
        Future f4 = Executors.newFixedThreadPool(15).submit(runnable);
        System.out.println("Reached firing f5: " + LocalDateTime.now());
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
                    System.out.println("F5 finished at: " + LocalDateTime.now());
                    Done = true;
                }
            }
        });
        for (int i = 0; i < 1000; i++) {
            Executors.newFixedThreadPool(15).submit(runnable);
        }
        System.out.println("Fired another 1000 request: " + LocalDateTime.now());
    }


    @Test
    public void asyncronizationTest()
    {
        /// Run this test in main
        MovieRepository movieRepository = new MovieRepositoryImpl(new TMDBContext());

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository.getMovieImages(550).get();
                System.out.println("1: " +r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository.getMovieImages(550).get();
                System.out.println("2: " +r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository.getMovieImages(550).get();
                System.out.println("3: " +r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieVideo>> r = movieRepository.getMovieVideos(550).get();
                System.out.println("4: " +r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository.getMovieImages(550).get();
                System.out.println("5: " +r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieImage>> r = movieRepository.getMovieImages(550).get();
                System.out.println("6: " +r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });


        CompletableFuture.runAsync(() -> {
            try {
                ResponseEntity<List<MovieVideo>> r = movieRepository.getMovieVideos(550).get();
                System.out.println("7: " +r.getBody());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        });

    }

    @Test
    public void getMovieDetailsById() {
    }

    @Test
    public void searchMovies() {
    }

    @Test
    public void searchInMovies() {
    }

    @Test
    public void getMovieImages() {
    }

    @Test
    public void getMovieVideos() {
    }

    @Test
    public void getMovieCast() {
    }
}