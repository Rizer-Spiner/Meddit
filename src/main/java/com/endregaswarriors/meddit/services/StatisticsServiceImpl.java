package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.*;
import com.endregaswarriors.meddit.models.api.GetMovieFavoriteReport;
import com.endregaswarriors.meddit.models.api.GetMovieReport;
import com.endregaswarriors.meddit.models.database.statistics.FavoriteMovieStats;
import com.endregaswarriors.meddit.models.database.statistics.TrendingMovieStats;
import com.endregaswarriors.meddit.repositories.internal.statistics.FavoriteMovieStatsRepository;
import com.endregaswarriors.meddit.repositories.internal.statistics.TrendingMovieStatsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService{

    private final TrendingMovieStatsRepository trendingMovieStatsRepository;
    private final FavoriteMovieStatsRepository favoriteMovieStatsRepository;

    public StatisticsServiceImpl(TrendingMovieStatsRepository trendingMovieStatsRepository, FavoriteMovieStatsRepository favoriteMovieStatsRepository) {
        this.trendingMovieStatsRepository = trendingMovieStatsRepository;
        this.favoriteMovieStatsRepository = favoriteMovieStatsRepository;
    }

    @Override
    public CompletableFuture<Response<MovieTrendingReport>> getTrendingStatisticsForSubreddit(GetMovieReport getMovieReport) {
        return CompletableFuture.supplyAsync(() -> {
            List<TrendingMovieStats> trendingMovieStats = trendingMovieStatsRepository
                    .findAllByTrendingMovieStatsPK_Subreddit_id(getMovieReport.getSubreddit_id());
            if(trendingMovieStats.isEmpty()){
                return new Response<>(Status.INTERNAL_ERROR);
            } else {
                return new Response<>(Status.SUCCESS,
                        MovieTrendingReport.builder().trendingMovieStatsList(trendingMovieStats).build());
            }
        });
    }

    @Override
    public CompletableFuture<Response<MovieFavoriteReport>> getFavoriteStatisticsForSubreddit(GetMovieReport getMovieReport) {
        return CompletableFuture.supplyAsync(() -> {
            List<FavoriteMovieStats> favoriteMovieStats = favoriteMovieStatsRepository
                    .findAllByFavoriteMovieStatsPK_Subreddit_id(getMovieReport.getSubreddit_id());
            if(favoriteMovieStats.isEmpty()){
                return new Response<>(Status.INTERNAL_ERROR);
            } else {
                return new Response<>(Status.SUCCESS,
                        MovieFavoriteReport.builder().favoriteMovieStats(favoriteMovieStats).build());
            }
        });
    }

    @Override
    public CompletableFuture<Response<List<TopMovie>>> getTrendingSubreddits(Integer page) {
        return CompletableFuture.supplyAsync(() -> {
            List<TrendingMovieStats> trendingMovieStats = trendingMovieStatsRepository.findCurrentTrendingTop(
                    LocalDate.now().minusWeeks(1), PageRequest.of(page, 20));
            if(trendingMovieStats.isEmpty()){
                return new Response<>(Status.INTERNAL_ERROR);
            } else {
                List<TopMovie> topMovies = trendingMovieStats.stream()
                        .map(trending -> TopMovie.builder()
                                .title(trending.getTrendingMovieStatsPK().getSubreddit().getTitle())
                                .rank(trending.getTrendingMovieStatsPK().getRank())
                                .build())
                        .collect(Collectors.toList());
                return new Response<>(Status.SUCCESS, topMovies);
            }
        });
    }

    @Override
    public CompletableFuture<Response<List<TopMovie>>> getFavoriteSubreddits(Integer page) {
        return CompletableFuture.supplyAsync(() -> {
            List<FavoriteMovieStats> favoriteMovieStats = favoriteMovieStatsRepository.findCurrentFavoriteTop(
                    LocalDate.now().minusWeeks(1), PageRequest.of(page, 20));
            if(favoriteMovieStats.isEmpty()){
                return new Response<>(Status.INTERNAL_ERROR);
            } else {
                List<TopMovie> topMovies = favoriteMovieStats.stream()
                        .map(favorite -> TopMovie.builder()
                                .title(favorite.getFavoriteMovieStatsPK().getSubreddit().getTitle())
                                .rank(favorite.getFavoriteMovieStatsPK().getRank())
                                .build())
                        .collect(Collectors.toList());
                return new Response<>(Status.SUCCESS, topMovies);
            }
        });
    }
}
