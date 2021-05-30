package com.endregaswarriors.meddit.models;

import com.endregaswarriors.meddit.models.database.MedditUser;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class MedditThread {

    private Long threadId;
    private MedditUser postedBy;
    private String threadTitle;
    private String threadContent;
    private Long upvoteCounter;
    private boolean upvotedByUser;
    private LocalDateTime postDate;
}
