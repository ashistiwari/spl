package com.spl.playerservice.service;

import com.spl.playerservice.dto.CreatePlayersRequest;
import com.spl.playerservice.dto.PlayersResponse;
import com.spl.playerservice.entity.Players;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PlayersService {

    PlayersResponse createPlayers(CreatePlayersRequest players);
    PlayersResponse updatePlayers(Long id, Players players);
    List<PlayersResponse> getAllPlayers();


    PlayersResponse getPlayerById(Long playerId);

    PlayersResponse softDeletePlayerById(Long playerId);

    List<PlayersResponse> searchPlayer(String name);
}
