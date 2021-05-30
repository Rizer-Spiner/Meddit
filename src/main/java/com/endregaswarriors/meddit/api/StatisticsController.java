package com.endregaswarriors.meddit.api;

import com.endregaswarriors.meddit.models.MovieFavoriteReport;
import com.endregaswarriors.meddit.models.MovieTrendingReport;
import com.endregaswarriors.meddit.models.TopMovie;
import com.endregaswarriors.meddit.models.api.GetMovieFavoriteReport;
import com.endregaswarriors.meddit.models.api.GetMovieReport;
import com.endregaswarriors.meddit.services.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/meddit/statistics")
@Api(tags = {"statistics"})
public class StatisticsController extends ControllerBase{

    StatisticsService statisticsService;

    public StatisticsController(StatisticsService service)
    {
        this.statisticsService = service;
    }

    @ApiOperation(value = "Get the Top Trending subreddits according to Meddit community ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the top trending movies"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/topTrending/{page}")
    CompletableFuture<ResponseEntity<List<TopMovie>>> getTopTrending(@PathVariable("page") Integer page)
    {
        return statisticsService.getTrendingSubreddits(page).thenCompose(this::map);
    }

    @ApiOperation(value = "Get the Top Favorite subreddits according to Meddit community ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the top favorite movies"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/topFavorite/{page}")
    CompletableFuture<ResponseEntity<List<TopMovie>>> getTopFavorite(@PathVariable("page") Integer page)
    {
        return statisticsService.getFavoriteSubreddits(page).thenCompose(this::map);
    }

    @ApiOperation(value = "Get a report of a subreddit popularity based on the trending performance on Meddit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the trending report of the subreddit"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/report/trending")
    CompletableFuture<ResponseEntity<MovieTrendingReport>> getTrendingReport(@RequestBody GetMovieReport getMovieReport)
    {
        return statisticsService.getTrendingStatisticsForSubreddit(getMovieReport).thenCompose(this::map);
    }

    @ApiOperation(value = "Get a report of a subreddit popularity based on the favorite performance on Meddit")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the favorite report of the subreddit"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @GetMapping("/report/favorite")
    CompletableFuture<ResponseEntity<MovieFavoriteReport>> getFavoriteReport(@RequestBody GetMovieReport getMovieReport)
    {
        return statisticsService.getFavoriteStatisticsForSubreddit(getMovieReport).thenCompose(this::map);
    }


}
