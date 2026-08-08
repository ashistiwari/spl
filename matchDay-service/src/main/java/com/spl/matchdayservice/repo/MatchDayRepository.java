package com.spl.matchdayservice.repo;

import com.spl.matchdayservice.entity.MatchDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MatchDayRepository extends JpaRepository<MatchDay, Long> {
 Optional<MatchDay> findByMatchDate(LocalDate date);
}
