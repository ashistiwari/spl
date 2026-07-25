package com.spl.matchservice.dto;

import com.spl.matchservice.enums.MatchStatus;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchResponse {


    private Long id;

    private String matchName;

    private LocalDate matchDate;

    private String venue;

    private Integer totalOvers;

    private MatchStatus status;
}
