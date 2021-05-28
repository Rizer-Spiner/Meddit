package com.endregaswarriors.meddit.models.database.keys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
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

}
