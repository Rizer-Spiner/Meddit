package com.endregaswarriors.meddit.models.database;

import com.endregaswarriors.meddit.models.database.keys.ThreadLikePK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreadLikes {

    @EmbeddedId
    private ThreadLikePK threadLikePK;
    private Boolean like;

}
