package com.endregaswarriors.meddit.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogTraffic {

    private Integer subreddit_id;
    private Integer user_id;

}
