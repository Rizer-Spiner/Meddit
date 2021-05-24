package com.endregaswarriors.meddit.models.database;

import com.sun.xml.bind.v2.TODO;
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
    // TODO: 5/24/2021 TELL JUSTINAS YOU CHANGED THIS 
    private LocalDateTime postDate;
}
