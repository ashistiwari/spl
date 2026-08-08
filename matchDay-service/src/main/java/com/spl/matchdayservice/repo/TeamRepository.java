package com.spl.matchdayservice.repo;

import com.spl.matchdayservice.entity.MatchDay;
import com.spl.matchdayservice.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {
    List<Team> findByMatchDayId(long id);
}
