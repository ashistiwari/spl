package com.spl.matchdayservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamPlayerResponse {
    private Long playerId;

    private Boolean captain;
}
