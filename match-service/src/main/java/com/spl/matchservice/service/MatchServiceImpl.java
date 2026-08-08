package com.spl.matchservice.service;

import com.spl.commonlibrary.exception.ResourceNotFoundException;
import com.spl.matchservice.dto.CreateMatchRequest;
import com.spl.matchservice.dto.MatchResponse;
import com.spl.matchservice.dto.MatchValidator;
import com.spl.matchservice.entity.Match;
import com.spl.matchservice.entity.MatchPlayer;
import com.spl.matchservice.enums.TeamNumber;
import com.spl.matchservice.mapper.MatchMapper;
import com.spl.matchservice.repo.MatchPlayerRepository;
import com.spl.matchservice.repo.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            matchPlayerRepository.save(player);
        }
       for (Long playerId:request.getTeamTwo().getPlayerids()){
           MatchPlayer player=MatchPlayer.builder().
                   match(match).
                   playerId(playerId).
                   team(TeamNumber.TEAM_TWO).
                   captain(playerId.equals(request.getTeamTwo().getCaptainId())).build();
           matchPlayerRepository.save(player);
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
