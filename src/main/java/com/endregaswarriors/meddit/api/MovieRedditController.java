package com.endregaswarriors.meddit.api;

import com.endregaswarriors.meddit.models.Movie;
import com.endregaswarriors.meddit.models.MovieSearchResult;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.services.MovieRedditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.ArrayList;
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
    public ResponseEntity<Movie> getMovieSubreddit(@PathVariable(value = "movieId") String movieId) {
//        return new ResponseEntity<>(new Movie("The Room"), HttpStatus.OK);
//        CompletableFuture<Response<Movie>>
        return null;
    }


    @GetMapping("/search/{queryParameter}")
    public ResponseEntity<List<MovieSearchResult>> getMovies(@PathVariable(value = "queryParameter") String queryParameter) {
//        List<MovieSearchResult> list = new ArrayList<>();
//        list.add(new MovieSearchResult("5555", "I am not a movie", "Check my status",
//                LocalDate.of(1999, 10, 01), 0.5, "not telling", 6.7));
//        list.add(new MovieSearchResult("5556", "I am also not a movie", "Check my status again",
//                LocalDate.of(1999, 10, 01), 0.5, "not telling", 6.7));
//        return new ResponseEntity<>(list, HttpStatus.OK);
        return null;
    }


}
