package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.MovieFavoriteReport;
import com.endregaswarriors.meddit.models.MovieTrendingReport;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.TopMovie;
import com.endregaswarriors.meddit.models.api.GetMovieFavoriteReport;
import com.endregaswarriors.meddit.models.api.GetMovieTrendingReport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class StatisticsServiceImpl implements StatisticsService{
    @Override
    public CompletableFuture<Response<MovieTrendingReport>> getTrendingStatisticsForSubreddit(GetMovieTrendingReport getMovieTrendingReport) {
        return null;
    }

    @Override
    public CompletableFuture<Response<MovieFavoriteReport>> getFavoriteStatisticsForSubreddit(GetMovieFavoriteReport getMovieFavoriteReport) {
        return null;
    }

    @Override
    public CompletableFuture<Response<List<TopMovie>>> getTrendingSubreddits(Integer page) {
        return null;
    }

    @Override
    public CompletableFuture<Response<List<TopMovie>>> getFavoriteSubreddits(Integer page) {
        return null;
    }
}
