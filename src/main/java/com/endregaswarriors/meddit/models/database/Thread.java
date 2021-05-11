package com.endregaswarriors.meddit.models.database;

import javax.persistence.*;

@Entity
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer thread_id;

    private Integer movie_id;
    private Integer user_id;
    private String title;
    private String content;
}
