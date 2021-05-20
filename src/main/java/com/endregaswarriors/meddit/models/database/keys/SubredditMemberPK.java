package com.endregaswarriors.meddit.models.database.keys;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class SubredditMemberPK {

    protected Integer subreddit_id;
    protected Integer user_id;

}
