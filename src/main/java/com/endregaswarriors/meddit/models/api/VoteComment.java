package com.endregaswarriors.meddit.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteComment {

    private Integer subredditId;
    private Long commentId;
    private Integer userId;
    private Boolean upvote;
}
