package com.endregaswarriors.meddit.models.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewUser {

    private String firebase_id;
    private String username;

}
