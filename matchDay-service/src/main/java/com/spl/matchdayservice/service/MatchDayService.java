package com.spl.matchdayservice.service;

import com.spl.matchdayservice.dto.CreateMatchDayRequest;
import com.spl.matchdayservice.dto.MatchDayResponse;
import com.spl.matchdayservice.dto.UpdateMatchResultRequest;

import java.util.List;

public interface MatchDayService {

    MatchDayResponse createMatchDay(CreateMatchDayRequest request);
    MatchDayResponse getMatchDay(Long matchDayId);
    List<MatchDayResponse> getAllMatchDays();
    MatchDayResponse completeMatchDay(Long matchDayId);
    MatchDayResponse updatematchResult(Long matchDayId, UpdateMatchResultRequest request);
}
