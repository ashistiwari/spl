package com.spl.matchdayservice.dto;

import com.spl.matchdayservice.enums.TeamNumber;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamResponse {

    private Long teamId;

    private TeamNumber teamNumber;

    private Integer wins;

    private Integer losses;

    private Integer ties;

    private List<TeamPlayerResponse> players;

}
