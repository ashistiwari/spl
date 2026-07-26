package com.spl.matchservice.repo;

import com.spl.matchservice.entity.MatchPlayer;
import com.spl.matchservice.enums.TeamNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchPlayerRepository extends JpaRepository<MatchPlayer, Long> {
    List<MatchPlayer> findByMatchId(Long matchId);
    List<MatchPlayer> findByMatchIdAndTeam(Long matchId, TeamNumber teamNumber);
}
