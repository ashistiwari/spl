package com.spl.matchdayservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate seriesDate;
    private Integer teamOneWins;
    private Integer teamTwoWins;
    private Integer ties;
    private Boolean active;
    @OneToMany(mappedBy="series")
    private List<MatchDay> matchDayList=new ArrayList<>();
}
