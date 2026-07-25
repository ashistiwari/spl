package com.spl.matchservice.service;

import com.spl.matchservice.dto.CreateMatchRequest;
import com.spl.matchservice.dto.MatchResponse;

import java.util.List;

public class MatchServiceImpl implements MatchService{
    @Override
    public MatchResponse createMatch(CreateMatchRequest request) {
        return null;
    }

    @Override
    public MatchResponse updateMatch(CreateMatchRequest request) {
        return null;
    }

    @Override
    public List<MatchResponse> getAllMatches() {
        return List.of();
    }

    @Override
    public MatchResponse getByMatchId(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
