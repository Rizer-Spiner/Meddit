package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.Comment;
import com.endregaswarriors.meddit.models.database.Thread;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query(" FROM Comment ct WHERE ct.thread.thread_id=:threadID")
    Optional<List<Comment>> findBySubredditId(@Param("threadID") Long threadId, @Param("page") int page );

    @Query(value = "SELECT COUNT(cl.like) FROM public.commentlikes cl where cl.comment_id =:commentID", nativeQuery = true)
    Optional<Long> getLikesByCommentId(@Param("commentID") Long comment_id);

    @Query(value = "SELECT cl.like FROM public.commentlikes cl WHERE cl.user_id=:userID AND cl.comment_id =:commentID", nativeQuery = true)
    Optional<Boolean> getLikeForUserByCommentId(@Param("userID") Integer userId, @Param("commentID") Long comment_id);

    @Query(value = "INSERT INTO public.commentlikes cl VALUES (:commentID, :userID, true)", nativeQuery = true)
    void upvoteComment(@Param("commentID") Long commentId, @Param("userID") Integer userId);

    @Query(value = "DELETE FROM public.commentlikes cl WHERE cl.comment_id=:commentID AND cl.user_id=:userID", nativeQuery = true)
    void downVoteComment(@Param("commentID") Long commentId,@Param("userID") Integer userId);

    @Query(value = "SELECT sc.parent_id FROM public.subcomment sc WHERE sc.comment_id =:commentID", nativeQuery = true)
    Optional<Long> getParentCommentId(@Param("commentID") Long commentId);
}
