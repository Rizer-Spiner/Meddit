package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.Subreddit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubredditRepository extends CrudRepository<Subreddit, Integer> {

    @Query("SELECT subreddit FROM Subreddit subreddit WHERE subreddit.movie_id = :id ")
    Optional<Subreddit> findSubredditByMovie_id(@Param("id") Integer id);

}
