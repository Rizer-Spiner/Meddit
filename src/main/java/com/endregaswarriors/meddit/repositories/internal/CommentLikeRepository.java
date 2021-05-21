package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.CommentLike;
import com.endregaswarriors.meddit.models.database.keys.CommentLikePK;
import org.springframework.data.repository.CrudRepository;

public interface CommentLikeRepository extends CrudRepository<CommentLike, CommentLikePK> {
}
