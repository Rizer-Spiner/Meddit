package com.endregaswarriors.meddit.models.database;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer thread_id;

    //TODO: add foreign key constraint
    //private Integer movie_id;
    private Integer user_id;
    private String title;
    private String content;
    private Integer counter;
    private LocalDate postDate;
}
