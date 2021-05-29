package com.endregaswarriors.meddit.models.database.statistics.keys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteMovieStatsPK implements Serializable {

    private Integer subreddit_id;
    private Integer rank;
    private LocalDate from_date;
    private LocalDate to_date;

}
