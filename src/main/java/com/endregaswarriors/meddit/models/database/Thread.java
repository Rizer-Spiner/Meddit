package com.endregaswarriors.meddit.models.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long thread_id;

    //TODO: add foreign key constraint
    //private Integer movie_id;
    private Integer user_id;
    private String title;
    private String content;
    private LocalDate postDate;
}
