package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.MedditUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedditApplication.class)
@AutoConfigureMockMvc
class MedditUserRepositoryTest {

    @Autowired
    MedditUserRepository medditUserRepository;

    @Test
    public void findMedditUserByFirebase_idAndUsername(){
        Optional<MedditUser> optionalMedditUser = medditUserRepository
                .login("TestTestTestTestTest", "TestUser");

        assertTrue(optionalMedditUser.isPresent());
    }

}