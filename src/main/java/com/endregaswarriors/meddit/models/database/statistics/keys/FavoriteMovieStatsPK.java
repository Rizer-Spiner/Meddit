package com.endregaswarriors.meddit.models.database.statistics.keys;

import com.endregaswarriors.meddit.models.database.Subreddit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteMovieStatsPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "subreddit_id")
    private Subreddit subreddit;
    private Integer rank;
    private LocalDate from_date;
    private LocalDate to_date;

}
