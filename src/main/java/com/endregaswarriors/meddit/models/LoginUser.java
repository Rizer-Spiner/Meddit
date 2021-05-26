package com.endregaswarriors.meddit.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUser {

    private String firebase_id;
    private String username;

}
