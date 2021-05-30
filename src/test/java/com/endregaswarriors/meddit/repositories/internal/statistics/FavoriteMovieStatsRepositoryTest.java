package com.endregaswarriors.meddit.repositories.internal.statistics;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.statistics.FavoriteMovieStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedditApplication.class)
@AutoConfigureMockMvc
class FavoriteMovieStatsRepositoryTest {

    @Autowired
    FavoriteMovieStatsRepository favoriteMovieStatsRepository;

    @Test
    void findAllByFavoriteMovieStatsPK_Subreddit_id() {
        List<FavoriteMovieStats> favoriteMovieStats = favoriteMovieStatsRepository.findAllByFavoriteMovieStatsPK_Subreddit_id(15);

        assertFalse(favoriteMovieStats.isEmpty());
    }

    @Test
    void findCurrentTop(){
        List<FavoriteMovieStats> favoriteMovieStats = favoriteMovieStatsRepository.findCurrentFavoriteTop(
                LocalDate.of(2021, 05, 28),
                PageRequest.of(0, 20));

        assertFalse(favoriteMovieStats.isEmpty());
    }
}