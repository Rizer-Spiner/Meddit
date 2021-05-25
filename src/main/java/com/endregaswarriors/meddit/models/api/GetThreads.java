package com.endregaswarriors.meddit.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetThreads {

    private Integer subredditId;
    private Integer userId;
    private int page;
}
