package com.endregaswarriors.meddit.models.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopMovieList {
    @Id
    private Integer user_id;

    private Integer subreddit1_id;
    private Integer subreddit2_id;
    private Integer subreddit3_id;
    private Integer subreddit4_id;
    private Integer subreddit5_id;
    private Integer subreddit6_id;
    private Integer subreddit7_id;
    private Integer subreddit8_id;
    private Integer subreddit9_id;
    private Integer subreddit10_id;

}
