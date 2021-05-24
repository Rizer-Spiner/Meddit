package com.endregaswarriors.meddit.repositories.internal;


import com.endregaswarriors.meddit.models.database.Thread;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThreadRepository extends CrudRepository<Thread, Long> {

    @Query(" FROM Thread td WHERE td.subreddit.subreddit_id=:subredditID")
    Optional<List<Thread>> findBySubredditId(@Param("subredditID") Integer subredditId );

    // TODO: 5/23/2021 amend to work with Crud Syntax 
    @Query(value = "SELECT COUNT(tl.like) FROM public.threadlikes tl where tl.thread_id =:threadID", nativeQuery = true)
    Optional<Long> getLikesByThreadId(@Param("threadID") Long threadID);

    @Query(value = "SELECT tl.like FROM public.threadlikes tl WHERE tl.user_id=:userID AND tl.thread_id =:threadID", nativeQuery = true)
    Optional<Boolean> getLikeForUserByThreadId(@Param("userID") Integer userId, @Param("threadID") Long threadId);
}
