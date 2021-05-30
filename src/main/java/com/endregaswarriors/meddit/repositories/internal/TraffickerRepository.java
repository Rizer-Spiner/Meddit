package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.Trafficker;
import com.endregaswarriors.meddit.models.database.keys.TraffickerPK;
import org.springframework.data.repository.CrudRepository;

public interface TraffickerRepository extends CrudRepository<Trafficker, TraffickerPK> {

}
