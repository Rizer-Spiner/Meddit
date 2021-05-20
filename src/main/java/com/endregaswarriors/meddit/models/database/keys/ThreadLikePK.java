package com.endregaswarriors.meddit.models.database.keys;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ThreadLikePK implements Serializable {

    protected Long thread_id;
    protected Integer user_id;

}
