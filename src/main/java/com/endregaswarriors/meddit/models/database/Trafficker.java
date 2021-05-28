package com.endregaswarriors.meddit.models.database;

import com.endregaswarriors.meddit.models.database.keys.TraffickerPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trafficker {
    @EmbeddedId
    private TraffickerPK traffickerPK;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MedditUser user_id;
}
