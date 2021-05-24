package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.Subreddit;
import com.endregaswarriors.meddit.models.database.Thread;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedditApplication.class)
@AutoConfigureMockMvc
class ThreadRepositoryTest {

    @Autowired
    ThreadRepository threadRepository;

    @Test
    void get(){
        Optional<Thread> databaseThread = threadRepository.findById(1L);

        assertTrue(databaseThread.isPresent());
    }

    @Test
    void insertNewThread(){
        Thread newThread = Thread.builder()
                .subreddit_id(1)
                .user(MedditUser.builder().user_id(2).build())
                .title("AAAAAA")
                .content("AAAAAAAAAAAAA")
                .postdate(LocalDate.now())
                .build();

        Thread savedThread = threadRepository.save(newThread);

        assertNotNull(savedThread);
    }

}