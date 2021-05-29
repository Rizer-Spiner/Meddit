package com.endregaswarriors.meddit.repositories.internal.statistics;

import com.endregaswarriors.meddit.models.database.statistics.TrendingMovieStats;
import com.endregaswarriors.meddit.models.database.statistics.keys.TrendingMovieStatsPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrendingMovieStatsRepository extends Repository<TrendingMovieStats, TrendingMovieStatsPK> {

    @Query("SELECT tms FROM TrendingMovieStats tms WHERE tms.trendingMovieStatsPK.subreddit_id = :subredditID")
    List<TrendingMovieStats> findAllByTrendingMovieStatsPK_Subreddit_id(@Param("subredditID") Integer subreddit_id);

}
