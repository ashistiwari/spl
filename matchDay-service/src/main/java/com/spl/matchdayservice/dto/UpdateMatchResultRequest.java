package com.spl.matchdayservice.dto;

import com.spl.matchdayservice.enums.TeamNumber;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UpdateMatchResultRequest {
    @NotNull
    private TeamNumber tossWInner;
    private TeamNumber matchWinner;
    @NotNull
    @Min(0)
    private Integer teamOneScore;
    @NotNull
    @Min(0)
    private Integer teamTwoScore;
}
