package com.spl.matchservice.dto;

import com.spl.matchservice.enums.TeamNumber;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchPlayerResponse {


    private Long playerId;
    private TeamNumber team;
    private Boolean captain;

    private Integer runs;
    private Integer ballsFaced;
    private Integer fours;
    private Integer sixes;

    private Integer ballsBowled;
    private Integer runsConceded;
    private Integer wickets;

    private Integer catches;

}