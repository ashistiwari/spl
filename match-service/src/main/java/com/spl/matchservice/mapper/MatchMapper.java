package com.spl.matchservice.mapper;

import com.spl.matchservice.dto.CreateMatchRequest;
import com.spl.matchservice.dto.MatchPlayerResponse;
import com.spl.matchservice.dto.MatchResponse;
import com.spl.matchservice.entity.Match;
import com.spl.matchservice.entity.MatchPlayer;
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
                .status(match.getStatus())
                .tossWinner(match.getTossWinner())
                .tossDecision(match.getTossDecision())
                .winner(match.getMatchWinner())
                .players(toPlayerResponse(match.getMatchPlayers()))
                .build();
    }
    private static List<MatchPlayerResponse> toPlayerResponse(List<MatchPlayer> player) {

        return player.stream().map(player1->MatchPlayerResponse.builder()
                .playerId(player1.getPlayerId())
                .team(player1.getTeam())
                .captain(player1.getCaptain())
                .runs(player1.getRuns())
                .ballsFaced(player1.getBallsFaced())
                .fours(player1.getFours())
                .ballsBowled(player1.getBallsBowled())
                .runsConceded(player1.getRunsConceded())
                .wickets(player1.getWickets())
                .build()).toList();
    }
    }
