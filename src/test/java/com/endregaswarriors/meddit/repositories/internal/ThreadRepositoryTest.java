package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.Subreddit;
import com.endregaswarriors.meddit.models.database.Thread;
import com.endregaswarriors.meddit.models.database.ThreadLikes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedditApplication.class)
@AutoConfigureMockMvc
class ThreadRepositoryTest {

    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    TheadLikesRepository threadLikesRepository;

    @Test
    void get(){
        Optional<Thread> databaseThread = threadRepository.findById(1L);

        assertTrue(databaseThread.isPresent());
    }

    @Test
    void integration_findBySubredditId()
    {
        Page<Thread> databaseThreads = threadRepository.findAllBySubreddit_id(1, PageRequest.of(0, 5, Sort.unsorted()));

        assertTrue(!databaseThreads.isEmpty());
        assertTrue(databaseThreads.getTotalElements()!=0);
        System.out.println(databaseThreads.getTotalElements());
        List<Thread> threads = databaseThreads.getContent();
        System.out.println(threads.size());
        for (Thread t: threads) {
            System.out.println(t.getUser().getUser_id());
        }

    }

    @Test
    void integration_getLikesByThreadId()
    {
        Optional<Long> likes = threadLikesRepository.getLikesByThread_id(1L);

        assertTrue(likes.isPresent());
        System.out.println(likes.get());
    }

    @Test
    void integration_getLikeForUserByThreadIdFalse()
    {
        Optional<ThreadLikes> like = threadLikesRepository.getLikeForUserByThreadId(1, 1L);

        assertTrue(like.isPresent());
        System.out.println(like.get());
    }

    @Test
    void integration_getLikeForUserByThreadIdTrue()
    {
        Optional<ThreadLikes> like = threadLikesRepository.getLikeForUserByThreadId(2, 1L);

        assertTrue(like.isPresent());
        System.out.println(like.get());
    }

    @Test
    void integration_getLikeForUserNotFound()
    {
        Optional<ThreadLikes> like = threadLikesRepository.getLikeForUserByThreadId(2, 2L);

        assertTrue(like.isEmpty());

    }

}