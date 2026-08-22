package com.spl.matchservice.entity;

import com.spl.commonlibrary.entity.BaseEntity;
import com.spl.matchservice.enums.MatchStatus;
import com.spl.matchservice.enums.TeamNumber;
import com.spl.matchservice.enums.TossDecision;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
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
    @FutureOrPresent
    private LocalDate matchDate;
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
    private Integer teamOneScore;
    private Integer teamTwoScore;
    private Boolean active;
    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    private List<MatchPlayer> matchPlayers=new ArrayList<>();
}
