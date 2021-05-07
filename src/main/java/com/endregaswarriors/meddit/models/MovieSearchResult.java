package com.endregaswarriors.meddit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchResult {

    @SerializedName(value = "id")
    private String tmdb_id;
    private String title;
    private String overview;
    private LocalDate release_Date;
    @SerializedName(value="vote_average")
    private double rating;
    private String poster_path;

    public MovieSearchResult() {
    }

    public MovieSearchResult(String tmdb_id, String title, String overview, LocalDate release_Date, double rating, String poster_path) {
        this.tmdb_id = tmdb_id;
        this.title = title;
        this.overview = overview;
        this.release_Date = release_Date;
        this.rating = rating;
        this.poster_path = poster_path;
    }

    public String getTmdb_id() {
        return tmdb_id;
    }

    public void setTmdb_id(String tmdb_id) {
        this.tmdb_id = tmdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public LocalDate getRelease_Date() {
        return release_Date;
    }

    public void setRelease_Date(LocalDate release_Date) {
        this.release_Date = release_Date;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public String toString() {
        return "MovieSearchResult{" +
                "tmdb_id='" + tmdb_id + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", release_Date=" + release_Date +
                ", rating=" + rating +
                ", poster_path='" + poster_path + '\'' +
                '}';
    }
}
