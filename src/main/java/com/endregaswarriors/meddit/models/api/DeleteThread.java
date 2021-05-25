package com.endregaswarriors.meddit.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class DeleteThread {

    private Long threadId;
    private Integer userId;
    private Integer subredditId;
}
