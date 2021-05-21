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

    public Movie(MovieDetails details, List<MovieImage> images, List<MovieVideo> videos, List<PersonDetails> cast, MedditInfo medditInfo) {
        this.details = details;
        this.images = images;
        this.videos = videos;
        this.cast = cast;
        this.medditInfo = medditInfo;
    }


    public MovieDetails getDetails() {
        return details;
    }

    public void setDetails(MovieDetails details) {
        this.details = details;
    }

    public List<MovieImage> getImages() {
        return images;
    }

    public void setImages(List<MovieImage> images) {
        this.images = images;
    }

    public List<MovieVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<MovieVideo> videos) {
        this.videos = videos;
    }

    public List<PersonDetails> getCast() {
        return cast;
    }

    public void setCast(List<PersonDetails> cast) {
        this.cast = cast;
    }

    public MedditInfo getMedditInfo() {
        return medditInfo;
    }

    public void setMedditInfo(MedditInfo medditInfo) {
        this.medditInfo = medditInfo;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "details=" + details +
                ", images=" + images +
                ", videos=" + videos +
                ", cast=" + cast +
                ", medditInfo=" + medditInfo +
                '}';
    }
}
