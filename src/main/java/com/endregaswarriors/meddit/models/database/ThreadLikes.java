package com.endregaswarriors.meddit.models.database;

import com.endregaswarriors.meddit.models.database.keys.ThreadLikePK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "threadlikes")
public class ThreadLikes {

    @EmbeddedId
    private ThreadLikePK threadLikePK;

}
