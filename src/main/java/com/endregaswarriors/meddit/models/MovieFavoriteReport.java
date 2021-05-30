package com.endregaswarriors.meddit.models;

import com.endregaswarriors.meddit.models.database.statistics.FavoriteMovieStats;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieFavoriteReport {

    List<FavoriteMovieStats> favoriteMovieStats;

}
