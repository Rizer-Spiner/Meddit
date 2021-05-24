package com.endregaswarriors.meddit.models;

import com.endregaswarriors.meddit.models.database.MedditUser;
import lombok.Builder;
import lombok.Data;


import java.time.LocalDate;

@Builder
@Data
public class MedditComment {

    private Long commentId;
    private Long threadId;
    private MedditUser postedBy;
    private String content;
    private Long upvoteCounter;
    private boolean upvotedByUser;
    private LocalDate postDate;
}
