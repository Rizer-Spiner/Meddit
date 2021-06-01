package com.endregaswarriors.meddit.repositories.internal.statistics;

import com.endregaswarriors.meddit.models.database.statistics.FavoriteMovieStats;
import com.endregaswarriors.meddit.models.database.statistics.TrendingMovieStats;
import com.endregaswarriors.meddit.models.database.statistics.keys.TrendingMovieStatsPK;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TrendingMovieStatsRepository extends Repository<TrendingMovieStats, TrendingMovieStatsPK> {

    @Query("SELECT tms FROM TrendingMovieStats tms WHERE tms.trendingMovieStatsPK.subreddit.subreddit_id = :subredditID")
    List<TrendingMovieStats> findAllByTrendingMovieStatsPK_Subreddit_id(@Param("subredditID") Integer subreddit_id);

    @Query("SELECT tms FROM TrendingMovieStats tms WHERE :currentDate BETWEEN tms.trendingMovieStatsPK.from_date AND tms.trendingMovieStatsPK.to_date ORDER BY tms.trendingMovieStatsPK.rank")
    List<TrendingMovieStats> findCurrentTrendingTop(@Param("currentDate") LocalDate currentDate, Pageable page);

}
