package com.spl.matchservice.service;

import com.spl.commonlibrary.exception.BadRequestException;
import com.spl.commonlibrary.exception.ResourceNotFoundException;
import com.spl.matchservice.dto.*;
import com.spl.matchservice.entity.Match;
import com.spl.matchservice.entity.MatchPlayer;
import com.spl.matchservice.enums.MatchStatus;
import com.spl.matchservice.enums.TeamNumber;
import com.spl.matchservice.mapper.MatchMapper;
import com.spl.matchservice.repo.MatchPlayerRepository;
import com.spl.matchservice.repo.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchServiceImpl implements MatchService{

    private MatchRepository matchRepository;
    private MatchPlayerRepository matchPlayerRepository;
    private MatchValidator matchValidator;
    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository, MatchPlayerRepository matchPlayerRepository, MatchValidator  matchValidator) {
        this.matchRepository=matchRepository;
        this.matchPlayerRepository=matchPlayerRepository;
        this.matchValidator=matchValidator;
    }
    @Override
    @Transactional
    public MatchResponse createMatch(CreateMatchRequest request) {
        matchValidator.validate(request);


        Match match = MatchMapper.toEntity(request);

        Match savedMatch = matchRepository.save(match);

        savePlayers(savedMatch, request);

        return MatchMapper.toResponse(savedMatch);
    }

    private void savePlayers(Match match, CreateMatchRequest request) {

        for(Long playerId:request.getTeamOne().getPlayerids()){
            MatchPlayer player=MatchPlayer.builder().match(match).
                    playerId(playerId).team(TeamNumber.TEAM_ONE).
                    captain(playerId.equals(request.getTeamOne().getCaptainId())).build();
            match.getMatchPlayers().add(player);
        }
       for (Long playerId:request.getTeamTwo().getPlayerids()){
           MatchPlayer player=MatchPlayer.builder().
                   match(match).
                   playerId(playerId).
                   team(TeamNumber.TEAM_TWO).
                   captain(playerId.equals(request.getTeamTwo().getCaptainId())).build();
           match.getMatchPlayers().add(player);
       }
/*
        List<MatchPlayer> players = new ArrayList<>();

        for (Long playerId : request.getTeamOne().getPlayerids()) {

            players.add(
                    MatchPlayer.builder()
                            .match(match)
                            .playerId(playerId)
                            .team(TeamNumber.TEAM_ONE)
                            .captain(
                                    playerId.equals(
                                            request.getTeamOne().getCaptainId()
                                    )
                            )
                            .build();
            match.getMatchPlayers().add(player);
            );
        }

        for (Long playerId : request.getTeamTwo().getPlayerids()) {

            players.add(
                    MatchPlayer.builder()
                            .match(match)
                            .playerId(playerId)
                            .team(TeamNumber.TEAM_TWO)
                            .captain(
                                    playerId.equals(
                                            request.getTeamTwo().getCaptainId()
                                    )
                            )
                            .build()
            );
        }

        matchPlayerRepository.saveAll(players);*/
    }

    @Override
    @Transactional
    public MatchResponse updateMatchResult(
            Long matchId,
            UpdateMatchResultRequest request) {

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Match not found with id: " + matchId
                        )
                );

        validateMatchResult(match, request);
        validatePlayerTeams(match, request);

        match.setTeamOneScore(request.getTeamOneScore());
        match.setTeamTwoScore(request.getTeamTwoScore());
        match.setMatchWinner(request.getMatchWinner());
        match.setStatus(MatchStatus.COMPLETED);

        updatePlayerStatistics(match, request);

        Match savedMatch = matchRepository.save(match);

        return MatchMapper.toResponse(savedMatch);
    }

    private void validateMatchResult(
            Match match,
            UpdateMatchResultRequest request) {

        if (Boolean.FALSE.equals(match.getActive())) {
            throw new BadRequestException(
                    "Cannot update an inactive match"
            );
        }

        if (match.getStatus() == MatchStatus.COMPLETED) {
            throw new BadRequestException(
                    "Match result has already been updated"
            );
        }

        if (request.getMatchWinner() == TeamNumber.TEAM_ONE
                && request.getTeamOneScore() <= request.getTeamTwoScore()) {

            throw new BadRequestException(
                    "Team one cannot be the winner"
            );
        }

        if (request.getMatchWinner() == TeamNumber.TEAM_TWO
                && request.getTeamTwoScore() <= request.getTeamOneScore()) {

            throw new BadRequestException(
                    "Team two cannot be the winner"
            );
        }

        if (request.getMatchWinner() == TeamNumber.TIE
                && !request.getTeamOneScore()
                .equals(request.getTeamTwoScore())) {

            throw new BadRequestException(
                    "A match can be declared a tie only when both scores are equal"
            );
        }
    }

    private void updatePlayerStatistics(
            Match match,
            UpdateMatchResultRequest request) {

        updateTeamPlayerStatistics(
                match,
                TeamNumber.TEAM_ONE,
                request.getTeamOne().getPlayermatchStatsRequestList()
        );

        updateTeamPlayerStatistics(
                match,
                TeamNumber.TEAM_TWO,
                request.getTeamTwo().getPlayermatchStatsRequestList()
        );
    }

    private void updateTeamPlayerStatistics(
            Match match,
            TeamNumber team,
            List<PlayerMatchStatsRequest> statistics) {

        Map<Long, PlayerMatchStatsRequest> statsMap =
                statistics.stream()
                        .collect(Collectors.toMap(
                                PlayerMatchStatsRequest::getPlayerId,
                                Function.identity()
                        ));

        for (MatchPlayer matchPlayer : match.getMatchPlayers()) {

            if (matchPlayer.getTeam() != team) {
                continue;
            }

            PlayerMatchStatsRequest stats =
                    statsMap.get(matchPlayer.getPlayerId());

            if (stats == null) {
                throw new BadRequestException(
                        "Statistics missing for player: "
                                + matchPlayer.getPlayerId()
                );
            }

            matchPlayer.setRuns(stats.getRuns());
            matchPlayer.setBallsFaced(stats.getBallsFaced());
            matchPlayer.setFours(stats.getFours());
            matchPlayer.setBallsBowled(stats.getBallsBowled());
            matchPlayer.setRunsConceded(stats.getRunsConceded());
            matchPlayer.setWickets(stats.getWickets());
        }
    }
    private void validatePlayerTeams(
            Match match,
            UpdateMatchResultRequest request) {

        validateTeamPlayers(
                match,
                TeamNumber.TEAM_ONE,
                request.getTeamOne().getPlayermatchStatsRequestList()
        );

        validateTeamPlayers(
                match,
                TeamNumber.TEAM_TWO,
                request.getTeamTwo().getPlayermatchStatsRequestList()
        );
    }
    private void validateTeamPlayers(
            Match match,
            TeamNumber team,
            List<PlayerMatchStatsRequest> statistics) {

        Set<Long> actualPlayerIds = match.getMatchPlayers()
                .stream()
                .filter(player -> player.getTeam() == team)
                .map(MatchPlayer::getPlayerId)
                .collect(Collectors.toSet());

        Set<Long> requestPlayerIds = statistics
                .stream()
                .map(PlayerMatchStatsRequest::getPlayerId)
                .collect(Collectors.toSet());

        if (!actualPlayerIds.equals(requestPlayerIds)) {
            throw new BadRequestException(
                    "Player statistics do not match the players of "
                            + team
            );
        }
    }

    @Override
    @Transactional
    public List<MatchResponse> getAllMatches() {

        return matchRepository.findAll()
                .stream()
                .map(MatchMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public MatchResponse getByMatchId(Long id) {
        Match match = matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Match not found"));

        return MatchMapper.toResponse(match);
    }

    @Override
    @Transactional
    public MatchResponse deleteById(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Match not found"));

        match.setActive(false);

        return MatchMapper.toResponse(matchRepository.save(match));
    }
}
