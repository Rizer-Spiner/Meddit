package com.endregaswarriors.meddit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
public class CommentSection {
    private Long threadID;
    private List<MedditComment> commentList;
}
