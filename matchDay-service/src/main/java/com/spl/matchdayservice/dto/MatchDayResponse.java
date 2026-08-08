package com.spl.matchdayservice.dto;

import com.spl.matchdayservice.enums.MatchDayStatus;
import lombok.*;
import org.springframework.beans.factory.config.YamlProcessor;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchDayResponse {

    private Long id;
    private LocalDate matchDate;
    private MatchDayStatus matchStatus;
    private List<TeamResponse> teams;
}
