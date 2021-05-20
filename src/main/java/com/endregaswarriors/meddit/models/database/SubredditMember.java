package com.endregaswarriors.meddit.models.database;

import com.endregaswarriors.meddit.models.database.keys.SubredditMemberPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubredditMember {
    @EmbeddedId
    SubredditMemberPK subredditMemberPK;
}
