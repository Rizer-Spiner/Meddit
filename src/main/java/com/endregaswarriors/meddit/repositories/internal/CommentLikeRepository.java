package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.CommentLike;
import com.endregaswarriors.meddit.models.database.keys.CommentLikePK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CommentLikeRepository extends CrudRepository<CommentLike, CommentLikePK> {

    @Query(value = "SELECT COUNT(cl.like) FROM public.commentlikes cl where cl.comment_id =:commentID", nativeQuery = true)
    Optional<Long> getLikesByCommentId(@Param("commentID") Long comment_id);

    @Query(value = "SELECT cl.like FROM public.commentlikes cl WHERE cl.user_id=:userID AND cl.comment_id =:commentID", nativeQuery = true)
    Optional<Boolean> getLikeForUserByCommentId(@Param("userID") Integer userId, @Param("commentID") Long comment_id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO public.commentlikes VALUES (:commentID, :userID, true)", nativeQuery = true)
    void upvoteComment(@Param("commentID") Long commentId, @Param("userID") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM public.commentlikes cl WHERE cl.comment_id=:commentID AND cl.user_id=:userID", nativeQuery = true)
    void downVoteComment(@Param("commentID") Long commentId,@Param("userID") Integer userId);
}
