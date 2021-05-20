package com.endregaswarriors.meddit.models.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubredditMember {
    @Id
    private Integer subreddit_id;
    @Id
    private Integer user_id;
}
