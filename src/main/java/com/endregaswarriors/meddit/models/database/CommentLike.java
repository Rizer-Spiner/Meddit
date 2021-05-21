package com.endregaswarriors.meddit.models.database;

import com.endregaswarriors.meddit.models.database.keys.CommentLikePK;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CommentLike {

    @EmbeddedId
    private CommentLikePK commentLikePK;
    private Boolean like;

}
