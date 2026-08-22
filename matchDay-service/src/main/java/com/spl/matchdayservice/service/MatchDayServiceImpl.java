package com.spl.matchdayservice.service;

import com.spl.matchdayservice.dto.CreateMatchDayRequest;
import com.spl.matchdayservice.dto.MatchDayResponse;
import com.spl.matchdayservice.dto.TeamRequest;
import com.spl.matchdayservice.dto.UpdateMatchResultRequest;
import com.spl.matchdayservice.entity.MatchDay;
import com.spl.matchdayservice.entity.Team;
import com.spl.matchdayservice.entity.TeamPlayer;
import com.spl.matchdayservice.enums.MatchDayStatus;
import com.spl.matchdayservice.enums.TeamNumber;
import com.spl.matchdayservice.mapper.MatchDayMapper;
import com.spl.matchdayservice.repo.MatchDayRepository;
import com.spl.matchdayservice.repo.TeamPlayerRepository;
import com.spl.matchdayservice.repo.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MatchDayServiceImpl implements MatchDayService {
    private final MatchDayRepository matchDayRepository;

    private final TeamRepository teamRepository;

    private final TeamPlayerRepository teamPlayerRepository;

    @Override
    public MatchDayResponse createMatchDay(CreateMatchDayRequest request) {
        MatchDay matchDay = MatchDayMapper.toEntity(request);
        MatchDay savedMatchDay = matchDayRepository.save(matchDay);
        createTeams(savedMatchDay,request);
        return MatchDayMapper.toResponse(savedMatchDay);
    }
    private void createTeams(MatchDay matchDay,CreateMatchDayRequest request) {
        Team teamOne = Team.builder()
                .teamNumber(TeamNumber.TEAM_ONE)
                .wins(0)
                .losses(0)
                .ties(0)
                .matchDay(matchDay)
                .build();

        Team teamTwo = Team.builder()
                .teamNumber(TeamNumber.TEAM_TWO)
                .wins(0)
                .losses(0)
                .ties(0)
                .matchDay(matchDay)
                .build();

        teamRepository.save(teamOne);

        teamRepository.save(teamTwo);

        savePlayers(teamOne,
                request.getTeamOne());

        savePlayers(teamTwo,
                request.getTeamTwo());
        matchDay.getTeams().add(teamOne);
        matchDay.getTeams().add(teamTwo);
    }
    private void savePlayers(Team team, TeamRequest teamRequest){
        for(Long playerId:teamRequest.getPlayerIds()){
            TeamPlayer player=TeamPlayer.builder()
                    .playerId(playerId)
                    .captain(playerId.equals(teamRequest.getCaptainId()))
                    .team(team)
                    .build();
            teamPlayerRepository.save(player);
            team.getPlayers().add(player);
        }

    }

    @Override
    public MatchDayResponse getMatchDay(Long matchDayId) {
        MatchDay matchDay = matchDayRepository.findById(matchDayId).orElseThrow(() -> new RuntimeException("MatchDay not found with id: " + matchDayId));
        return MatchDayMapper.toResponse(matchDay);
    }

    @Override
    public List<MatchDayResponse> getAllMatchDays() {
        return matchDayRepository.findAll().stream()
                .map(MatchDayMapper::toResponse)
                .toList();
    }
    @Override
    public MatchDayResponse completeMatchDay(Long id){
        MatchDay matchDay = matchDayRepository.findById(id).orElseThrow(() -> new RuntimeException("MatchDay not found with id: " + id));
        matchDay.setStatus(MatchDayStatus.COMPLETED);
        return MatchDayMapper.toResponse(matchDayRepository.save(matchDay));
    }

    @Override
    public MatchDayResponse updatematchResult(Long matchDayId, UpdateMatchResultRequest request) {
        MatchDay matchDay = matchDayRepository.findById(matchDayId)
                                                .orElseThrow(() -> new RuntimeException("MatchDay not found with id: " + matchDayId));
        if (matchDay.getStatus()==MatchDayStatus.COMPLETED) {
            throw new RuntimeException("MatchDay already completed");
        }
        matchDay.setMatchWinner(request.getMatchWinner());
        matchDay.setTossWinner(request.getTossWInner());
        matchDay.setTeamOneScore(request.getTeamOneScore());
        matchDay.setTeamTwoScore(request.getTeamTwoScore());
        matchDay.setStatus(MatchDayStatus.COMPLETED);
        matchDay.setActive(false);
        MatchDay savedmatchDay = matchDayRepository.save(matchDay);
        updateTeamStatistics(savedmatchDay);
        return MatchDayMapper.toResponse(savedmatchDay);
    }

    private void updateTeamStatistics(MatchDay matchDay){
        TeamNumber winner=matchDay.getMatchWinner();
        for(Team team: matchDay.getTeams()){
            if(winner == null){
                team.setTies(team.getTies()+1);
            } else if (team.getTeamNumber() == winner) {
                team.setWins(team.getWins()+1);
            } else {
                team.setLosses(team.getLosses()+1);
                
            }
        }
        teamRepository.saveAll(matchDay.getTeams());
    }
}
