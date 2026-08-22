package com.spl.matchservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MatchTeamStatsRequest {

    private List<PlayerMatchStatsRequest> playermatchStatsRequestList;
}
