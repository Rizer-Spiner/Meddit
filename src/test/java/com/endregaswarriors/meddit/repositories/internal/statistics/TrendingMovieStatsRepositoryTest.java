package com.endregaswarriors.meddit.repositories.internal.statistics;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.statistics.TrendingMovieStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedditApplication.class)
@AutoConfigureMockMvc
class TrendingMovieStatsRepositoryTest {

    @Autowired
    TrendingMovieStatsRepository trendingMovieStatsRepository;

    @Test
    public void findAllByTrendingMovieStatsPK_Subreddit_id(){
        List<TrendingMovieStats> trendingMovieStats = trendingMovieStatsRepository
                .findAllByTrendingMovieStatsPK_Subreddit_id(1);

        assertFalse(trendingMovieStats.isEmpty());
    }

}