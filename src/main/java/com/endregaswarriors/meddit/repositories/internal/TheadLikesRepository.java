package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.ThreadLikes;
import com.endregaswarriors.meddit.models.database.keys.ThreadLikePK;
import org.springframework.data.repository.CrudRepository;

public interface TheadLikesRepository extends CrudRepository<ThreadLikes, ThreadLikePK> {
}
