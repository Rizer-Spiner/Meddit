package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.api.GetMovieTrendingReport;
import com.endregaswarriors.meddit.models.MovieFavoriteReport;
import com.endregaswarriors.meddit.models.MovieTrendingReport;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.TopMovie;
import com.endregaswarriors.meddit.models.api.GetMovieFavoriteReport;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface StatisticsService {

    CompletableFuture<Response<MovieTrendingReport>> getTrendingStatisticsForSubreddit(GetMovieTrendingReport getMovieTrendingReport);
    CompletableFuture<Response<MovieFavoriteReport>> getFavoriteStatisticsForSubreddit(GetMovieFavoriteReport getMovieFavoriteReport);
    CompletableFuture<Response<List<TopMovie>>> getTrendingSubreddits(Integer page);
    CompletableFuture<Response<List<TopMovie>>> getFavoriteSubreddits(Integer page);
}
