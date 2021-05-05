package com.endregaswarriors.meddit.models;

import com.endregaswarriors.meddit.repositories.external.JSONExtractFactory;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;
import java.util.Date;

@JsonDeserialize(using = JSONExtractFactory.TMDBMovieExtractor.class)
public class MovieDetails {

    String id;
    String title;
    String synopsis;
    String posterPath;
    LocalDate releaseDate;
    int runtime;
    String status;
    double rating;


    public MovieDetails(String id, String title, String synopsis, String posterPath, LocalDate releaseDate, int runtime, String status, double rating) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.status = status;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate=" + releaseDate +
                ", runtime=" + runtime +
                ", status='" + status + '\'' +
                ", rating=" + rating +
                '}';
    }
}
