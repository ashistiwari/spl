package com.spl.matchservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerMatchStatsRequest {
    @NotNull
    private Long playerId;

    @NotNull
    @Min(0)
    private Integer runs;

    @NotNull
    @Min(0)
    private Integer ballsFaced;

    @NotNull
    @Min(0)
    private Integer fours;

    @NotNull
    @Min(0)
    private Integer ballsBowled;

    @NotNull
    @Min(0)
    private Integer runsConceded;

    @NotNull
    @Min(0)
    private Integer wickets;
}
