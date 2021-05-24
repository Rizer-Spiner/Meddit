package com.endregaswarriors.meddit.models.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long thread_id;
    @ManyToOne
    @JoinColumn(name = "subreddit_id")
    private Subreddit subreddit;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MedditUser user;
    private String title;
    private String content;
    // TODO: 5/22/2021 ask Justinas if LocalDateTime if ok
    private LocalDateTime postdate;
}
