package com.spl.matchdayservice.repo;

import com.spl.matchdayservice.entity.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamPlayerRepository extends JpaRepository<TeamPlayer,Long> {
    List<TeamPlayer> findByTeamId(Long id);
}
