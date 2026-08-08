package com.spl.matchservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchTeamRequest {
    @NotEmpty(message = "player ids are required")
    private List<Long> playerids;
    @NotNull(message = "captain id is required")
    private Long captainId;
}
