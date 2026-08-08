package com.spl.matchdayservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMatchDayRequest {
    @FutureOrPresent
    private LocalDate matchDate;
    @Valid
    private TeamRequest teamOne;
    @Valid
    private TeamRequest teamTwo;
}
