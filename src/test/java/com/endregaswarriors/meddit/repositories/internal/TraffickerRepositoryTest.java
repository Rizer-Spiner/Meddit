package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.MedditApplication;
import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.Trafficker;
import com.endregaswarriors.meddit.models.database.keys.TraffickerPK;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedditApplication.class)
@AutoConfigureMockMvc
class TraffickerRepositoryTest {

    @Autowired
    TraffickerRepository traffickerRepository;

    @Test
    public void insertSomeTrafficEntries(){
        Trafficker trafficker = Trafficker.builder()
                .traffickerPK(TraffickerPK.builder().subreddit_id(1).timestamp(LocalDateTime.now()).build())
                .user_id(MedditUser.builder().user_id(1).build())
                .build();

        Trafficker databaseTrafficker = traffickerRepository.save(trafficker);

        assertNotNull(databaseTrafficker.getTraffickerPK().getTimestamp());
    }
}