package com.spl.matchdayservice.entity;

import com.spl.commonlibrary.entity.BaseEntity;
import com.spl.matchdayservice.enums.MatchDayStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchDay extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate matchDate;

    @Enumerated(EnumType.STRING)
    private MatchDayStatus status;

    private Boolean active;

    @OneToMany(
            mappedBy="matchDay",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<Team> teams = new ArrayList<>();
}

