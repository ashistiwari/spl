package com.spl.matchdayservice.mapper;

import com.spl.matchdayservice.dto.TeamPlayerResponse;
import com.spl.matchdayservice.dto.TeamResponse;
import com.spl.matchdayservice.entity.Team;

public class TeamMapper {

    public static TeamResponse toResponse(Team team){
        return TeamResponse.builder()
                .teamId(team.getId())
                .teamNumber(team.getTeamNumber())
                .wins(team.getWins())
                .losses(team.getLosses())
                .ties(team.getTies())
                .players(team.getPlayers().stream()
                        .map(TeamPlayerMapper::toResponse)
                        .toList())
                .build();
    }
}
