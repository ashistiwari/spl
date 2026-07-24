package com.spl.playerservice.controller;

import com.spl.commonlibrary.dto.ApiResponse;
import com.spl.playerservice.dto.CreatePlayersRequest;
import com.spl.playerservice.dto.PlayersResponse;
import com.spl.playerservice.entity.Players;
import com.spl.playerservice.service.PlayersService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayersController {
    private PlayersService playersService;
    @Autowired
    public PlayersController(PlayersService playersService) {
        this.playersService=playersService;
    }
    @PostMapping("/playersRequest")
    public ResponseEntity<PlayersResponse> createPlayers(@Valid @RequestBody CreatePlayersRequest playersRequest) {
        PlayersResponse players = playersService.createPlayers(playersRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(players);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<PlayersResponse> updatePlayers(@PathVariable @Positive(message = "Player id must be positive") Long id, @RequestBody Players playersRequest) {
        PlayersResponse players = playersService.updatePlayers(id, playersRequest);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PlayersResponse> getPlayers(@PathVariable
                                                          @Positive(message = "Player id must be positive") Long id) {
        PlayersResponse players = playersService.getPlayerById(id);
        return ResponseEntity.ok(players);
    }
    @GetMapping("/get")
    public ResponseEntity<List<PlayersResponse>> getAllPlayers(){
        List<PlayersResponse> players = playersService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{playerId}")
    public ResponseEntity<PlayersResponse> softDeletePlayerById(@PathVariable
                                                                    @Positive(message = "Player id must be positive") Long playerId) {
        PlayersResponse players = playersService.softDeletePlayerById(playerId);
        return ResponseEntity.ok(players);
    }
    @GetMapping("/search")
    public ResponseEntity<List<PlayersResponse>> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(@RequestParam String name) {
        List<PlayersResponse> players = playersService.searchPlayer(name);
        return ResponseEntity.ok(players);
    }
}
