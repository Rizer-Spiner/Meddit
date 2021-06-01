package com.endregaswarriors.meddit.models.api;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserTopMovies {
    private Integer user_id;
    private Integer[] movieIds;
}
