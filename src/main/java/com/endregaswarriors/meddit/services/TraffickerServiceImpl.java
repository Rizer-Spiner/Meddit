package com.endregaswarriors.meddit.services;

import com.endregaswarriors.meddit.models.database.MedditUser;
import com.endregaswarriors.meddit.models.database.Trafficker;
import com.endregaswarriors.meddit.models.database.keys.TraffickerPK;
import com.endregaswarriors.meddit.repositories.internal.TraffickerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TraffickerServiceImpl implements TraffickerService {

    TraffickerRepository traffickerRepository;

    public TraffickerServiceImpl(TraffickerRepository traffickerRepository) {
        this.traffickerRepository = traffickerRepository;
    }

    @Override
    public void logTraffic(Integer subreddit_id, Integer user_id) {
        Trafficker trafficker = Trafficker.builder()
                .traffickerPK(TraffickerPK.builder()
                        .subreddit_id(subreddit_id)
                        .timestamp(LocalDateTime.now())
                        .user_id(MedditUser.builder().user_id(user_id).build())
                        .build())
                .build();

        traffickerRepository.save(trafficker);
    }
}
