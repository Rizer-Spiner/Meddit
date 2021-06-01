package com.endregaswarriors.meddit.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopMovie {

    private Integer subreddit_id;
    private Integer rank;

}
