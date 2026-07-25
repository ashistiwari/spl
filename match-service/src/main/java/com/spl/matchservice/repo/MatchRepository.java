package com.spl.matchservice.repo;

import com.spl.matchservice.entity.Match;
import com.spl.matchservice.enums.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByMatchStatus(MatchStatus status);
    List<Match> findByMatchDate(LocalDate date);
    boolean existsByMatchDateAndTeamOneIdAndTeamTwoId(
            LocalDate date,
            Long teamOneId,
            Long teamTwoId
    );
}
