package com.endregaswarriors.meddit.models.api;

import com.endregaswarriors.meddit.models.MedditComment;
import com.endregaswarriors.meddit.models.database.MedditUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentSection {
    private Long threadID;
    private List<MedditComment> commentList;
}
