package com.endregaswarriors.meddit.repositories.internal.statistics;

import com.endregaswarriors.meddit.models.database.statistics.FavoriteMovieStats;
import com.endregaswarriors.meddit.models.database.statistics.keys.FavoriteMovieStatsPK;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface FavoriteMovieStatsRepository extends Repository<FavoriteMovieStats, FavoriteMovieStatsPK> {

    List<FavoriteMovieStats> findAllByFavoriteMovieStatsPK_Subreddit_id(Integer subreddit_id);

}
