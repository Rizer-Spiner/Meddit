package com.endregaswarriors.meddit.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddComment {
    private Integer subredditId;
    private Long threadId;
    private Integer userId;
    private String content;
    private Long previousCommentId;
}
