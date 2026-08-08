package com.spl.matchservice.service;

import com.spl.matchservice.dto.CreateMatchRequest;
import com.spl.matchservice.dto.MatchResponse;

import java.util.List;

public interface MatchService {

    MatchResponse createMatch(CreateMatchRequest request);
    List<MatchResponse> getAllMatches();
    MatchResponse getByMatchId(Long id);
    MatchResponse deleteById(Long id);
}
