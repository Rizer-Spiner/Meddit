package com.endregaswarriors.meddit.api;

import com.endregaswarriors.meddit.models.Movie;
import com.endregaswarriors.meddit.models.MovieSearchResult;


import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.services.MovieRedditService;
import com.endregaswarriors.meddit.services.TraffickerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/meddit/movieSubreddit")
@Api(tags = {"Subreddits"})
public class MovieRedditController extends ControllerBase {

    private MovieRedditService movieRedditService;
    private TraffickerService traffickerService;

    public MovieRedditController(MovieRedditService movieRedditService, TraffickerService traffickerService) {
        this.movieRedditService = movieRedditService;
        this.traffickerService = traffickerService;
    }

    @ApiOperation(value = "Get full information regarding a movie subreddit", response = Movie.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved subreddit information"),
            @ApiResponse(code = 404, message = "The subreddit related to the movie id could not be found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/{movieId}")
    public CompletableFuture<ResponseEntity<Movie>> getMovieSubreddit(@PathVariable(value = "movieId") Integer movieId,
                                                                      @RequestParam Integer user_id
                                                                ) {
        MedditUser user = MedditUser.builder().user_id(user_id).build();
        return CompletableFuture.supplyAsync(() -> {
            try {
                Response<Movie> movieResponse = movieRedditService.getMovieDetails(movieId).get();
                CompletableFuture.runAsync(() -> {
                    traffickerService.logTraffic(
                            Integer.parseInt(movieResponse.getModel().getDetails().getImdb_id().substring(2)),
                            user.getUser_id());
                });
                if(movieResponse.getStatus().equals(Status.SUCCESS)){
                    return new ResponseEntity<Movie>(movieResponse.getModel(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<Movie>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        });
    }

    @ApiOperation(value = "Get a list of movies matching with query parameter ", response = MovieSearchResult[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved search result"),
            @ApiResponse(code = 204, message = "The query returns 0 results"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/search/{queryParameter}")
    public CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchMovies(@PathVariable(value = "queryParameter") String queryParameter) {

        return movieRedditService.searchForMovie(queryParameter).thenComposeAsync(s -> {
            if (s.isEmpty())
                return custom(204);
            if (s == null)
                return custom(500);
            else return success(s);
        });
    }

    @ApiOperation(value = "Get a list of movies matching with query parameter ", response = MovieSearchResult[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved search result"),
            @ApiResponse(code = 204, message = "The query returns 0 results"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("search/category/{queryParameter}")
    public CompletableFuture<ResponseEntity<List<MovieSearchResult>>> searchCategoryOfMovies(@PathVariable String queryParameter)
    {
        return movieRedditService.searchMoviesByCategory(queryParameter).thenComposeAsync(s->{
            if (s.isEmpty())
                return custom(204);
            if (s == null)
                return custom(500);
            else return success(s);
        });
    }

}
