package com.endregaswarriors.meddit.models.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Subreddit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subreddit_id;

    private Integer movie_id;
    private LocalDate created;
}
