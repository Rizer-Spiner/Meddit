package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.*;
import com.endregaswarriors.meddit.models.database.Subreddit;
import com.endregaswarriors.meddit.repositories.external.MovieRepository;
import com.endregaswarriors.meddit.repositories.external.MovieRepositoryImpl;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import com.endregaswarriors.meddit.repositories.internal.SubredditRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class MovieRedditServiceImpl implements MovieRedditService{

    MovieRepository movieRepository;
    SubredditMembersRepository subredditMembersRepository;
    SubredditRepository subredditRepository;

    public MovieRedditServiceImpl(MovieRepository movieRepository,
                                  SubredditMembersRepository subredditMembersRepository,
                                  SubredditRepository subredditRepository) {
        this.movieRepository = movieRepository;
        this.subredditMembersRepository = subredditMembersRepository;
        this.subredditRepository = subredditRepository;
    }

    @Override
    public CompletableFuture<List<MovieSearchResult>> searchForMovie(String keyword) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<ResponseEntity<List<MovieSearchResult>>> response = movieRepository.searchMovies(keyword, 1);
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
    public CompletableFuture<List<MovieSearchResult>> searchMoviesByCategory(String category) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<ResponseEntity<List<MovieSearchResult>>> response = movieRepository.searchInMovies(category, 1);
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
    public CompletableFuture<Response<Movie>> getMovieDetails(Integer TMDB_id) {
        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<ResponseEntity<MovieDetails>> details = movieRepository.getMovieDetailsById(TMDB_id);
            CompletableFuture<ResponseEntity<List<MovieImage>>> images = movieRepository.getMovieImages(TMDB_id);
            CompletableFuture<ResponseEntity<List<MovieVideo>>> videos = movieRepository.getMovieVideos(TMDB_id);
            CompletableFuture<ResponseEntity<List<PersonDetails>>> cast = movieRepository.getMovieCast(TMDB_id);

            CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(details, images, videos, cast);

            try {
                combinedFuture.get(5, TimeUnit.SECONDS);

                ResponseEntity<MovieDetails> detailsResponse = details.get();
                ResponseEntity<List<MovieImage>> imagesResponse = images.get();
                ResponseEntity<List<MovieVideo>> videoResponse = videos.get();
                ResponseEntity<List<PersonDetails>> castResponse = cast.get();

                if(detailsResponse.getStatusCode().is2xxSuccessful() &&
                    imagesResponse.getStatusCode().is2xxSuccessful() &&
                    videoResponse.getStatusCode().is2xxSuccessful() &&
                    castResponse.getStatusCode().is2xxSuccessful()){

                    //Some TMDB movies don't have an IMDB id
                    if(Objects.requireNonNull(detailsResponse.getBody()).getImdb_id().equals("null")){
                        return new Response<>(Status.NOT_FOUND);
                    }

                    MedditInfo info = getMedditInfo(detailsResponse.getBody());

                    Movie movie = Movie.builder()
                        .details(detailsResponse.getBody())
                        .images(imagesResponse.getBody())
                        .videos(videoResponse.getBody())
                        .cast(castResponse.getBody())
                        .medditInfo(info)
                        .build();
                    return new Response<>(Status.SUCCESS, movie);
                } else {
                    return new Response<>(Status.NOT_FOUND);
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                return new Response<>(Status.INTERNAL_ERROR);
            }
        });
    }

    private MedditInfo getMedditInfo(MovieDetails movieDetails) {
        Integer IMDB_id = Integer.parseInt(movieDetails.getImdb_id().substring(2));
        Optional<Subreddit> optionalSubreddit = subredditRepository.findSubredditByMovie_id(IMDB_id);

        if(optionalSubreddit.isEmpty()){
            Subreddit newSubreddit = Subreddit.builder()
                    .movie_id(IMDB_id)
                    .title(movieDetails.getTitle())
                    .build();
            Subreddit savedSubreddit = subredditRepository.save(newSubreddit);

            return MedditInfo.builder()
                    .subreddit_id(savedSubreddit.getSubreddit_id())
                    .members(0)
                    .creationDate(savedSubreddit.getCreated())
                    .build();
        } else {
            return MedditInfo.builder()
                    .members(subredditMembersRepository.countSubredditMembers(IMDB_id))
                    .creationDate(optionalSubreddit.get().getCreated())
                    .build();
        }
    }

}
