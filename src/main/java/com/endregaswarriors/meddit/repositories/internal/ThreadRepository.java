package com.endregaswarriors.meddit.repositories.internal;


import com.endregaswarriors.meddit.models.database.Thread;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ThreadRepository extends PagingAndSortingRepository<Thread, Long> {

    @Query(" FROM Thread td WHERE td.subreddit_id=:subredditID")
    Page<Thread> findAllBySubreddit_id(@Param("subredditID") Integer subredditId, Pageable page);

}
