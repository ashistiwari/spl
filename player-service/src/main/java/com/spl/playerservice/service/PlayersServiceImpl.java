package com.spl.playerservice.service;

import com.spl.commonlibrary.exception.BadRequestException;
import com.spl.commonlibrary.exception.DuplicateResourceException;
import com.spl.commonlibrary.exception.ResourceNotFoundException;
import com.spl.playerservice.dto.CreatePlayersRequest;
import com.spl.playerservice.dto.PlayersResponse;
import com.spl.playerservice.entity.Players;
import com.spl.playerservice.mapper.PlayersMapper;
import com.spl.playerservice.repo.PlayersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PlayersServiceImpl implements PlayersService {

    private final PlayersRepository playersRepository;
    private final PlayersMapper mapper;

    public PlayersServiceImpl(PlayersRepository playersRepository, PlayersMapper mapper) {
        this.playersRepository = playersRepository;
        this.mapper = mapper;
    }

    @Override
    public PlayersResponse createPlayers(CreatePlayersRequest players) {
        if (playersRepository.existsByMobile(players.getMobile())) {
            throw new DuplicateResourceException("Player already exists");
        }
        if(players.getAge()<10){
            throw new BadRequestException("Invalid age");
        }
        if(players.getMobile().length()!=10){
            throw new BadRequestException("Invalid mobile number");
        }
        Players player= PlayersMapper.toEntity(players);

        playersRepository.save(player);
        return PlayersMapper.toResponse(player);
    }

    @Override
    public PlayersResponse updatePlayers(Long id, Players players) {
        Players existingPlayer = playersRepository.findById(id).orElseThrow(() -> new BadRequestException("Player not found with the given Id"));
        existingPlayer.setFirstName(players.getFirstName());
        existingPlayer.setLastName(players.getLastName());
        existingPlayer.setMobile(players.getMobile());
        existingPlayer.setEmail(players.getEmail());
        existingPlayer.setAge(players.getAge());
        existingPlayer.setRole(players.getRole());
        existingPlayer.setBattingStyle(players.getBattingStyle());
        existingPlayer.setBowlingStyle(players.getBowlingStyle());
        Players updatedPlayer = playersRepository.save(existingPlayer);
        return PlayersMapper.toResponse(updatedPlayer);
    }

    @Override
    public List<PlayersResponse> getAllPlayers() {
        List<Players> players = playersRepository.findAll();
        return players.stream().map(PlayersMapper::toResponse).toList();
    }

    @Override
    public PlayersResponse getPlayerById(Long playerId) {
        Players player= playersRepository.findById(playerId).orElseThrow(()->new BadRequestException("Player not found"));
        return PlayersMapper.toResponse(player);
    }

    @Override
    public PlayersResponse softDeletePlayerById(Long playerId) {
        Players player = playersRepository.findById(playerId).orElseThrow(()->new ResourceNotFoundException("Player not found"));
        player.setActive(false);
        Players savedPlayer = playersRepository.save(player);
        return PlayersMapper.toResponse(savedPlayer);

    }

    @Override
    public List<PlayersResponse> searchPlayer(String name) {

        return playersRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name,name)
                .stream()
                .map(PlayersMapper::toResponse)
                .toList();
    }
}
