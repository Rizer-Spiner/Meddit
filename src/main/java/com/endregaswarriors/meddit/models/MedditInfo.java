package com.endregaswarriors.meddit.models;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class MedditInfo {

    private Integer members;
    private LocalDate creationDate;

}
