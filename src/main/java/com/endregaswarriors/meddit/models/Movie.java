package com.endregaswarriors.meddit.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Movie {

    private MovieDetails details;
    private List<MovieImage> images;
    private List<MovieVideo> videos;
    private List<PersonDetails> cast;
    private MedditInfo medditInfo;

}
