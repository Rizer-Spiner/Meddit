package com.endregaswarriors.meddit.repositories.internal.statistics;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.statistics.TrendingMovieStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
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

    @Test
    public void findCurrentTrendingTop(){
        List<TrendingMovieStats> trendingMovieStats = trendingMovieStatsRepository.findCurrentTrendingTop(
                LocalDate.now(),
                PageRequest.of(0, 20));

        assertFalse(trendingMovieStats.isEmpty());
    }

}