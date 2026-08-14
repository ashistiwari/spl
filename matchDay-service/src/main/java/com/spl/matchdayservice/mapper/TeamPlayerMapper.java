package com.spl.matchdayservice.mapper;

import com.spl.matchdayservice.dto.TeamPlayerResponse;
import com.spl.matchdayservice.entity.TeamPlayer;

public class TeamPlayerMapper {
    public static TeamPlayerResponse toResponse(TeamPlayer teamPlayer){
        return TeamPlayerResponse.builder()
                .playerId(teamPlayer.getPlayerId())
                .captain(teamPlayer.getCaptain())
                .build();
    }
}
