package com.spl.matchservice.entity;

import com.spl.matchservice.enums.TeamNumber;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Enumerated(EnumType.STRING)
    private TeamNumber team;

    private Boolean captain;

    // Batting
    private Integer runs;

    private Integer ballsFaced;

    private Integer fours;

    // Bowling
    private Integer ballsBowled;

    private Integer runsConceded;

    private Integer wickets;

}
