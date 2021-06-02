package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.ThreadLikes;
import com.endregaswarriors.meddit.models.database.keys.ThreadLikePK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TheadLikesRepository extends CrudRepository<ThreadLikes, ThreadLikePK> {

    @Query("SELECT COUNT(tl) FROM ThreadLikes tl where tl.threadLikePK.thread_id =:threadID")
    Optional<Long> getLikesByThread_id(@Param("threadID") Long threadID);

    @Query("SELECT tl FROM ThreadLikes tl WHERE tl.threadLikePK.user_id=:userID AND tl.threadLikePK.thread_id =:threadID")
    Optional<ThreadLikes> getLikeForUserByThreadId(@Param("userID") Integer userId, @Param("threadID") Long threadId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO public.threadlikes VALUES (:threadID, :userID)", nativeQuery = true)
    void upvoteThread(@Param("threadID") Long threadId, @Param("userID") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM public.threadlikes tl WHERE tl.thread_id=:threadID AND tl.user_id=:userID", nativeQuery = true)
    void downVoteThread(@Param("threadID") Long threadId,@Param("userID") Integer userId);
}
