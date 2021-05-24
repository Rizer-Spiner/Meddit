package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.Thread;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

    @Test
    void integration_findBySubredditId()
    {
        Optional<List<Thread>> databaseThreads = threadRepository.findBySubredditId(1);

        assertTrue(databaseThreads.isPresent());
        assertTrue(databaseThreads.get().size()!=0);
        System.out.println(databaseThreads.get().size());
        List<Thread> threads = databaseThreads.get();
        for (Thread t: threads) {
            System.out.println(t.getTitle());
        }

    }

    @Test
    void integration_getLikesByThreadId()
    {
        Optional<Long> likes = threadRepository.getLikesByThreadId(1L);

        assertTrue(likes.isPresent());
        System.out.println(likes.get());
    }

    @Test
    void integration_getLikeForUserByThreadIdFalse()
    {
        Optional<Boolean> like = threadRepository.getLikeForUserByThreadId(1, 1L);

        assertTrue(like.isPresent());
        System.out.println(like.get());
    }

    @Test
    void integration_getLikeForUserByThreadIdTrue()
    {
        Optional<Boolean> like = threadRepository.getLikeForUserByThreadId(2, 1L);

        assertTrue(like.isPresent());
        System.out.println(like.get());
    }

    @Test
    void integration_getLikeForUserNotFound()
    {
        Optional<Boolean> like = threadRepository.getLikeForUserByThreadId(2, 2L);

        assertTrue(like.isEmpty());

    }

}