package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.MedditThread;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.api.AddThread;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import com.endregaswarriors.meddit.repositories.internal.ThreadRepository;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ThreadServiceImplTest {


    @Autowired(required = true)
    ThreadRepository threadRepository;
    @Autowired(required = true)
    SubredditMembersRepository membersRepository;

    ThreadService subject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subject = new ThreadServiceImpl(threadRepository, membersRepository);
    }

    @Test
    public void addThread_Success() throws ExecutionException, InterruptedException {
        AddThread addThread = AddThread.builder()
                .subredditId(1)
                .threadTitle("This is a new thread")
                .threadContent("Content of this beautiful new thread")
                .postedByUserId(1)
                .build();
        Response<MedditThread> addedThread = subject.addThread(addThread).get();

        System.out.println(addedThread.getStatus().name());
        assertEquals(addedThread.getStatus(), Status.SUCCESS);

        MedditThread newThread = addedThread.getModel();
        System.out.println(newThread.toString());


    }

    @Test
    public void deleteThread() {
    }

    @Test
    public void getSubredditThreads() {
    }
}