package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.Subreddit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedditApplication.class)
@AutoConfigureMockMvc
class SubredditRepositoryTest {

    @Autowired
    SubredditRepository repository;

    @Test
    void get(){
        Optional<Subreddit> optionalSubreddit = repository.findById(1);

        assertTrue(optionalSubreddit.isPresent());
    }

    @Test
    void findSubredditByMovieId_exists(){
        Optional<Subreddit> optionalSubreddit = repository.findSubredditByMovie_id(126029);

        assertTrue(optionalSubreddit.isPresent());
    }
}