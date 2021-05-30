package com.endregaswarriors.meddit.models.database.statistics;

import com.endregaswarriors.meddit.models.database.statistics.keys.TrendingMovieStatsPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trendingmoviestats")
public class TrendingMovieStats {

    @EmbeddedId
    private TrendingMovieStatsPK trendingMovieStatsPK;

}
