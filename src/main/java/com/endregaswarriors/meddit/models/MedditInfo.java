package com.endregaswarriors.meddit.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
public class MedditInfo {

    private Integer subreddit_id;
    private Integer members;
    private LocalDate creationDate;


}
