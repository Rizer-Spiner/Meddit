package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.api.GetMovieReport;
import com.endregaswarriors.meddit.models.MovieFavoriteReport;
import com.endregaswarriors.meddit.models.MovieTrendingReport;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.TopMovie;
import com.endregaswarriors.meddit.models.api.GetMovieFavoriteReport;


import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface StatisticsService {

    CompletableFuture<Response<MovieTrendingReport>> getTrendingStatisticsForSubreddit(GetMovieReport getMovieReport);
    CompletableFuture<Response<MovieFavoriteReport>> getFavoriteStatisticsForSubreddit(GetMovieReport getMovieFavoriteReport);
    CompletableFuture<Response<List<TopMovie>>> getTrendingSubreddits(Integer page);
    CompletableFuture<Response<List<TopMovie>>> getFavoriteSubreddits(Integer page);
}
