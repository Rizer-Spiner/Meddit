package com.endregaswarriors.meddit.models.database.keys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubredditMemberPK implements Serializable {

    protected Integer subreddit_id;
    protected Integer user_id;

}
