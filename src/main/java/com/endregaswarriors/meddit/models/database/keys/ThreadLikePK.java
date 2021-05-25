package com.endregaswarriors.meddit.models.database.keys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadLikePK implements Serializable {

    protected Long thread_id;
    protected Integer user_id;

}
