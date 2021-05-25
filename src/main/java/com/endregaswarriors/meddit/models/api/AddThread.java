package com.endregaswarriors.meddit.models.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddThread {
    private Integer subredditId;
    private Integer postedByUserId;
    private String threadTitle;
    private String threadContent;
}
