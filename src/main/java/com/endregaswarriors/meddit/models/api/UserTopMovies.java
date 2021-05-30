package com.endregaswarriors.meddit.models.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserTopMovies {
    private Integer user_id;
    private Integer[] movieIds;
}
