package com.endregaswarriors.meddit.models.api;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetMovieReport {

    private Integer subreddit_id;
    private LocalDate from;
    private LocalDate to;

}
