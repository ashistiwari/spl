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
    private Long matchId;
    @Enumerated(EnumType.STRING)
    private TeamNumber team;
    private Boolean captain;

}
