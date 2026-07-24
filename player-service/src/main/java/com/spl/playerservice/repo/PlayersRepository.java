package com.spl.playerservice.repo;

import com.spl.playerservice.dto.PlayersResponse;
import com.spl.playerservice.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PlayersRepository extends JpaRepository<Players, Long> {
 boolean existsByEmail(String email);
 Boolean existsByMobile(String mobile);


    Optional<Players> findByEmail(String email);
    List<Players> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
