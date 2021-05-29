package com.endregaswarriors.meddit.models.database.statistics;

import com.endregaswarriors.meddit.models.database.statistics.keys.FavoriteMovieStatsPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteMovieStats {

    @EmbeddedId
    private FavoriteMovieStatsPK favoriteMovieStatsPK;

}
