package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
