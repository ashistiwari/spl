package com.spl.matchdayservice.entity;

import com.spl.matchdayservice.enums.TeamNumber;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TeamNumber teamNumber;

    private Integer wins;

    private Integer losses;

    private Integer ties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="match_day_id")
    private MatchDay matchDay;

    @OneToMany(
            mappedBy="team",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<TeamPlayer> players = new ArrayList<>();
}
