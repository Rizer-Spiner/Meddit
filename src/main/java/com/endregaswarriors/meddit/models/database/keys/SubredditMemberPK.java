package com.endregaswarriors.meddit.models.database.keys;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class SubredditMemberPK implements Serializable {

    protected Integer subreddit_id;
    protected Integer user_id;

}
