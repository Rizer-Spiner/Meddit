package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedditApplication.class)
@AutoConfigureMockMvc
class SubredditMembersRepositoryTest {

    @Autowired
    SubredditMembersRepository repository;

    @Test
    void countSubredditMembers_ifExists() {
        assertEquals(1, repository.countSubredditMembers(15414));
    }

    @Test
    void countSubredditMembers_empty() {
        assertEquals(0, repository.countSubredditMembers(126029));
    }

    @Test
    void getById() {
        boolean result = repository.existsById(SubredditMemberPK.builder().
                subreddit_id(1).
                user_id(1).
                build());

        assertTrue(result);
        System.out.println(result);
    }
}