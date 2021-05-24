package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.MedditUser;
import org.springframework.data.repository.CrudRepository;

public interface MedditUserRepository extends CrudRepository<MedditUser, Integer> {
}
