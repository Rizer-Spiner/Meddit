package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.MedditThread;
import com.endregaswarriors.meddit.models.Response;
import com.endregaswarriors.meddit.models.Status;
import com.endregaswarriors.meddit.models.api.AddThread;
import com.endregaswarriors.meddit.repositories.internal.SubredditMembersRepository;
import com.endregaswarriors.meddit.repositories.internal.TheadLikesRepository;
import com.endregaswarriors.meddit.repositories.internal.ThreadRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;


@SpringBootTest(classes = MedditApplication.class)
@AutoConfigureMockMvc
public class ThreadServiceImplTest {

    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    TheadLikesRepository threadLikesRepository;
    @Autowired
    SubredditMembersRepository membersRepository;

    @Test
    public void addThread_Success() throws ExecutionException, InterruptedException {

        ThreadService subject = new ThreadServiceImpl(threadRepository, threadLikesRepository,membersRepository);
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