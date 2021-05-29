package com.endregaswarriors.meddit.repositories.internal.statistics;

import com.endregaswarriors.meddit.models.database.statistics.TrendingMovieStats;
import com.endregaswarriors.meddit.models.database.statistics.keys.TrendingMovieStatsPK;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TrendingMovieStatsRepository extends Repository<TrendingMovieStats, TrendingMovieStatsPK> {

    List<TrendingMovieStats> findAllByTrendingMovieStatsPK_Subreddit_id(Integer subreddit_id);

}
