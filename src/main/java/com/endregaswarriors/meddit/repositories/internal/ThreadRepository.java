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

    // TODO: 5/24/2021 Add also the page
    @Query(" FROM Thread td WHERE td.subreddit_id=:subredditID")
    Optional<List<Thread>> findBySubredditId(@Param("subredditID") Integer subredditId, @Param("page") int page );

    // TODO: 5/23/2021 amend to work with Crud Syntax 
    @Query(value = "SELECT COUNT(tl.like) FROM public.threadlikes tl where tl.thread_id =:threadID", nativeQuery = true)
    Optional<Long> getLikesByThreadId(@Param("threadID") Long threadID);

    @Query(value = "SELECT tl.like FROM public.threadlikes tl WHERE tl.user_id=:userID AND tl.thread_id =:threadID", nativeQuery = true)
    Optional<Boolean> getLikeForUserByThreadId(@Param("userID") Integer userId, @Param("threadID") Long threadId);

    @Query(value = "INSERT INTO public.threadlikes tl VALUES (:threadID, :userID, true)", nativeQuery = true)
    void upvoteThread(@Param("threadID") Long threadId, @Param("userID") Integer userId);

    @Query(value = "DELETE FROM public.threadlikes tl WHERE tl.thread_id=:threadID AND tl.user_id=:userID", nativeQuery = true)
    void downVoteThread(@Param("threadID") Long threadId,@Param("userID") Integer userId);


}
