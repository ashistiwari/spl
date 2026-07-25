package com.spl.matchservice.entity;

import com.spl.commonlibrary.entity.BaseEntity;
import com.spl.matchservice.enums.MatchStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matchName;
    private String venue;
    private Integer totalOvers;
    private Long teamOneId;
    private Long teamTwoId;
    private LocalDate matchDate;
    private MatchStatus status;
    private Long tossWinnerTeamId;
    private Long matchWinnerTeamId;
    private Boolean active;
}
