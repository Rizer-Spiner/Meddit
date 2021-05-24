package com.endregaswarriors.meddit.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddThread {
    private Integer subredditId;
    private Integer postedByUserId;
    private String threadTitle;
    private String threadContent;
}
