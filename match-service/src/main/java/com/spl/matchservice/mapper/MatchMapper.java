package com.spl.matchservice.mapper;

import com.spl.matchservice.dto.CreateMatchRequest;
import com.spl.matchservice.dto.MatchPlayerResponse;
import com.spl.matchservice.dto.MatchResponse;
import com.spl.matchservice.entity.Match;
import com.spl.matchservice.enums.MatchStatus;

import java.util.Collections;
import java.util.List;

public class MatchMapper {

    public MatchMapper(){}

    public static Match toEntity(CreateMatchRequest request) {

        return Match.builder()
                .matchDate(request.getMatchDate())
                .totalOvers(request.getTotalOvers())
                .status(MatchStatus.SCHEDULED)
                .tossWinner(request.getTossWinner())
                .tossDecision(request.getTossDecision())
                .matchWinner(null)
                .active(true)
                .build();
    }

    public static MatchResponse toResponse(Match match) {

        List<MatchPlayerResponse> players =
                match.getMatchPlayers()
                        .stream()
                        .map(player -> MatchPlayerResponse.builder()
                                .playerId(player.getPlayerId())
                                .team(player.getTeam())
                                .captain(player.getCaptain())
                                .build())
                        .toList();

        /*List<MatchPlayerResponse> players =
                match.getMatchPlayers() == null
                        ? Collections.emptyList()
                        : match.getMatchPlayers().stream()
                          .map(this::toPlayerResponse)
                          .toList();*/

        return MatchResponse.builder()
                .id(match.getId())
                .matchDate(match.getMatchDate())
                .overs(match.getTotalOvers())
                .tossWinner(match.getTossWinner())
                .tossDecision(match.getTossDecision())
                .winner(match.getMatchWinner())
                .players(players)
                .build();
    }
    }
