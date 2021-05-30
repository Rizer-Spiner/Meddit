package com.endregaswarriors.meddit.models.database;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;
    private Long thread_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MedditUser user;
    private String content;
    private LocalDateTime postdate;
}
