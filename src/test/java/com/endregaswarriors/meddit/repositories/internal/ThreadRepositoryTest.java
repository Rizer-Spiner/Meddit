package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.Thread;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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

}