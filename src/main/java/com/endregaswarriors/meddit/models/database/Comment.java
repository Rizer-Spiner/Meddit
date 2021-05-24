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
    @ManyToOne
    @JoinColumn(name = "thread_id")
    private Thread thread;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MedditUser user;

    private String content;
    private LocalDateTime postDate;
}
