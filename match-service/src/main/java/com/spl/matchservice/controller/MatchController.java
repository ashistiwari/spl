package com.spl.matchservice.controller;

import com.spl.matchservice.dto.CreateMatchRequest;
import com.spl.matchservice.dto.MatchResponse;
import com.spl.matchservice.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/create")
    public ResponseEntity<MatchResponse> createMatch(@RequestBody CreateMatchRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createMatch(request));
    }



    @GetMapping("/getAll")
    public ResponseEntity<List<MatchResponse>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MatchResponse> getByMatchId(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getByMatchId(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        matchService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
