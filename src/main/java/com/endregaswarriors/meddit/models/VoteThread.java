package com.endregaswarriors.meddit.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VoteThread {
    private Integer subredditId;
    private Long threadId;
    private Integer userId;
    private Boolean upvote;
}
