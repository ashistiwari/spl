package com.spl.matchservice.dto;

import com.spl.matchservice.enums.TeamNumber;
import com.spl.matchservice.enums.TossDecision;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMatchRequest {


    @FutureOrPresent
    private LocalDate matchDate;



    @Min(5)
    @Max(8)
    private Integer totalOvers;

    @Valid
    private MatchTeamRequest teamOne;
    @Valid
    private MatchTeamRequest teamTwo;

    @NotNull
    private Long captainTeamOne;

    @NotNull
    private Long captainTeamTwo;

    @NotNull
    private TeamNumber tossWinner;

    @NotNull
    private TossDecision tossDecision;

}
