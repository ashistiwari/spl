package com.spl.matchservice.dto;

import com.spl.matchservice.enums.TeamNumber;
import com.spl.matchservice.enums.TossDecision;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchSummaryResponse {
    private Long matchId;

    private LocalDate matchDate;

    private Integer overs;

    private TeamNumber tossWinner;

    private TossDecision  tossDecision;

    private Boolean active;
}
