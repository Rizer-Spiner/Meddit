package com.endregaswarriors.meddit.models.database.keys;

import com.endregaswarriors.meddit.models.database.MedditUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraffickerPK implements Serializable {

    protected Integer subreddit_id;
    protected LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MedditUser user_id;

}
