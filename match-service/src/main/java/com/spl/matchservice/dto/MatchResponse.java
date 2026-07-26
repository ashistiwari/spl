package com.spl.matchservice.dto;

import com.spl.matchservice.enums.MatchStatus;
import com.spl.matchservice.enums.TeamNumber;
import com.spl.matchservice.enums.TossDecision;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchResponse {


    private Long id;

    private LocalDate matchDate;

    private MatchStatus status;

    private Integer totalOvers;

    private TeamNumber tossWinner;

    private TossDecision tossDecision;
    private TeamNumber matchWinner;
}
