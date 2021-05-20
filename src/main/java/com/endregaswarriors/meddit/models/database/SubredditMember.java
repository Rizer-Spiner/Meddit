package com.endregaswarriors.meddit.models.database;

import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "subredditmembers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubredditMember {
    @EmbeddedId
    SubredditMemberPK subredditMemberPK;
}
