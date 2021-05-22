package com.endregaswarriors.meddit.models.database;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@Data
public class Subreddit {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subreddit_id;

    private Integer movie_id;
    private LocalDate created;

    public Subreddit() {

    }

    public Subreddit(Integer subreddit_id, Integer movie_id, LocalDate created) {
        this.subreddit_id = subreddit_id;
        this.movie_id = movie_id;
        this.created = created;
    }

    public Integer getSubreddit_id() {
        return subreddit_id;
    }

    public void setSubreddit_id(Integer subreddit_id) {
        this.subreddit_id = subreddit_id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }
}
