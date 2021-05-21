package com.endregaswarriors.meddit.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddThread {
    private Integer subredditId;
    private String postedByUserId;
    private String threadTitle;
    private String threadContent;
}
