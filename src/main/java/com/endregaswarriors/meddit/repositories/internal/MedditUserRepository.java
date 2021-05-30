package com.endregaswarriors.meddit.repositories.internal;

import com.endregaswarriors.meddit.models.database.MedditUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MedditUserRepository extends CrudRepository<MedditUser, Integer> {

    @Query("SELECT user FROM MedditUser user WHERE user.firebase_id=:firebase AND user.username=:username")
    Optional<MedditUser> login(@Param("firebase") String firebase_id, @Param("username") String username);

}
