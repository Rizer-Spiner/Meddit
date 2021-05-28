package com.endregaswarriors.meddit.models;


import lombok.Builder;
import lombok.Data;


import java.util.List;

@Builder
@Data
public class CommentSection {
    private Long threadID;
    private List<MedditComment> commentList;
}
