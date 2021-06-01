package com.endregaswarriors.meddit.models;

import com.endregaswarriors.meddit.models.database.statistics.TrendingMovieStats;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieTrendingReport {

    List<TrendingMovieStats> trendingMovieStatsList;

}
