package com.endregaswarriors.meddit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetails {


    long id;
    String title;
    String overview;
    String poster_path;
    LocalDate release_date;
    int runtime;
    String status;
    @SerializedName(value = "vote_average")
    double rating;

    public MovieDetails()
    {

    }

    public MovieDetails(long id, String title, String overview, String poster_path, LocalDate release_date, int runtime, String status, double rating) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.runtime = runtime;
        this.status = status;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setIid(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return overview;
    }

    public void setSynopsis(String synopsis) {
        this.overview = synopsis;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
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

    public double getVote_average() {
        return rating;
    }

    public void setVote_average(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", synopsis='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", release_date=" + release_date +
                ", runtime=" + runtime +
                ", status='" + status + '\'' +
                ", rating=" + rating +
                '}';
    }
}
