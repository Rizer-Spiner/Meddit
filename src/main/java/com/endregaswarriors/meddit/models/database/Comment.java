package com.endregaswarriors.meddit.models.database;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;
    @ManyToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MedditUser user;

    private String content;
    private LocalDate postDate;
}
