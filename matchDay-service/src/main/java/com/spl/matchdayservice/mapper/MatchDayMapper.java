package com.spl.matchdayservice.mapper;

import com.spl.matchdayservice.dto.CreateMatchDayRequest;
import com.spl.matchdayservice.dto.MatchDayResponse;
import com.spl.matchdayservice.dto.TeamPlayerResponse;
import com.spl.matchdayservice.dto.TeamResponse;
import com.spl.matchdayservice.entity.MatchDay;
import com.spl.matchdayservice.entity.Team;
import com.spl.matchdayservice.enums.MatchDayStatus;

import java.util.List;

public class MatchDayMapper {
    public static MatchDay toEntity(CreateMatchDayRequest request) {
        MatchDay matchDay = MatchDay.builder()

                .matchDate(request.getMatchDate())
                .status(MatchDayStatus.ACTIVE)
                .active(true)
                .build();
        return matchDay;
    }

    public static MatchDayResponse toResponse(MatchDay matchDay) {
        List<TeamResponse> teams=matchDay.getTeams().stream()
                .map(team-> TeamResponse.builder().
                        teamId(team.getId()).
                        teamNumber(team.getTeamNumber()).
                        wins(team.getWins()).
                        losses(team.getLosses()).
                        ties(team.getTies()).
                        players(team.getPlayers().stream().map(player-> TeamPlayerResponse.builder().playerId(player.getPlayerId()).captain(player.getCaptain()).build()).toList()).
                        build()
                ).
                toList();
        return MatchDayResponse.builder().
                id(matchDay.getId()).
                matchDate(matchDay.getMatchDate()).
                matchStatus(matchDay.getStatus()).
                teams(teams).
                build();
    }
}
