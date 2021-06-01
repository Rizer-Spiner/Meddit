package com.endregaswarriors.meddit.models.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetMovieReport {

    private Integer subreddit_id;

}
