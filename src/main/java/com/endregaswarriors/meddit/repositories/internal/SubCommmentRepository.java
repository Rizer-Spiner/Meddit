package com.endregaswarriors.meddit.repositories.internal;


import com.endregaswarriors.meddit.models.database.SubComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubCommmentRepository extends CrudRepository<SubComment, Long> {

    @Query(value = "SELECT sc.parent_id FROM public.subcomment sc WHERE sc.comment_id =:commentID", nativeQuery = true)
    Optional<Long> getParentCommentId(@Param("commentID") Long commentId);

}
