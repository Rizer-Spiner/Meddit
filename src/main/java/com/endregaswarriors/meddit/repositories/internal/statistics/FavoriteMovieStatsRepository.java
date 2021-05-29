package com.endregaswarriors.meddit.repositories.internal.statistics;

import com.endregaswarriors.meddit.models.database.Thread;
import com.endregaswarriors.meddit.models.database.statistics.FavoriteMovieStats;
import com.endregaswarriors.meddit.models.database.statistics.keys.FavoriteMovieStatsPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FavoriteMovieStatsRepository extends Repository<FavoriteMovieStats, FavoriteMovieStatsPK> {

    @Query("SELECT fms FROM FavoriteMovieStats fms WHERE fms.favoriteMovieStatsPK.subreddit_id=:subredditID")
    List<FavoriteMovieStats> findAllByFavoriteMovieStatsPK_Subreddit_id(@Param("subredditID") Integer subreddit_id);

    @Query(" FROM FavoriteMovieStats fms WHERE :currentDate BETWEEN fms.favoriteMovieStatsPK.from_date AND fms.favoriteMovieStatsPK.to_date ORDER BY fms.favoriteMovieStatsPK.rank")
    List<FavoriteMovieStats> findCurrentFavoriteTop(@Param("currentDate")LocalDate currentDate, Pageable page);

}
