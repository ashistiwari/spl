package com.spl.matchservice.entity;

import com.spl.commonlibrary.entity.BaseEntity;
import com.spl.matchservice.enums.MatchStatus;
import com.spl.matchservice.enums.TeamNumber;
import com.spl.matchservice.enums.TossDecision;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cricket_match")
public class Match extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long matchDayId;
    private Long teamOneId;
    private Long teamTwoId;
    private Integer matchNumber;
    private Integer totalOvers;
    @Enumerated(EnumType.STRING)
    private MatchStatus status;
    @Enumerated(EnumType.STRING)
    private TossDecision tossDecision;
    @Enumerated(EnumType.STRING)
    private TeamNumber tossWinner;
    @Enumerated(EnumType.STRING)
    private TeamNumber matchWinner;
    private Boolean active;
}
