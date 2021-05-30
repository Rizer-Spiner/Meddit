package com.endregaswarriors.meddit.models;

import com.endregaswarriors.meddit.models.database.MedditUser;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Builder
@Data
public class MedditComment {

    private Long commentId;
    private MedditUser postedBy;
    private String content;
    private Long upvoteCounter;
    private boolean upvotedByUser;
    private Long parentCommentId;
    private LocalDateTime postDate;
}
