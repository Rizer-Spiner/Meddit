package com.endregaswarriors.meddit.models.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedditUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer user_id;
    String firebase_id;
    String username;

}
