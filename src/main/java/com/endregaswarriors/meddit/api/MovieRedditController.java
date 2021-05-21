package com.endregaswarriors.meddit.api;

import com.endregaswarriors.meddit.models.Movie;
import com.endregaswarriors.meddit.models.MovieSearchResult;


import com.endregaswarriors.meddit.services.MovieRedditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/meddit/movieSubreddit")
@Api(tags = {"Subreddits"})
public class MovieRedditController extends ControllerBase {

    private MovieRedditService movieRedditService;

    public MovieRedditController(MovieRedditService movieRedditService) {
        this.movieRedditService = movieRedditService;
    }

    @ApiOperation(value = "Get full information regarding a movie subreddit", response = Movie.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved subreddit information"),
            @ApiResponse(code = 404, message = "The subreddit related to the movie id could not be found")
    })
    @GetMapping("/{movieId}")
    public CompletableFuture<ResponseEntity<Movie>> getMovieSubreddit(@PathVariable(value = "movieId") Integer movieId) {

        return movieRedditService.getMovieDetails(movieId).thenCompose(this::map);
    }

    @ApiOperation(value = "Get a list of movies matching with query parameter ", response = MovieSearchResult[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved search result"),
            @ApiResponse(code = 204, message = "The query returns 0 results")
    })
    @GetMapping("/search/{queryParameter}")
    public CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchMovies(@PathVariable(value = "queryParameter") String queryParameter) {

        return movieRedditService.searchForMovie(queryParameter).thenComposeAsync(s -> {
            if (s.isEmpty() || s == null)
                return custom(204, null);
            else return success(s);
        });
    }

}
