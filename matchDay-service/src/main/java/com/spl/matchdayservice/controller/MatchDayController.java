package com.spl.matchdayservice.controller;

import com.spl.matchdayservice.dto.CreateMatchDayRequest;
import com.spl.matchdayservice.dto.MatchDayResponse;
import com.spl.matchdayservice.dto.UpdateMatchResultRequest;
import com.spl.matchdayservice.service.MatchDayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matchDay")
@RequiredArgsConstructor
public class MatchDayController {
    private final MatchDayService matchDayService;

    @PostMapping
    public ResponseEntity<MatchDayResponse> createMatchDay(@Valid @RequestBody CreateMatchDayRequest request) {
        MatchDayResponse response = matchDayService.createMatchDay(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MatchDayResponse> get(
            @PathVariable Long id){

        return ResponseEntity.ok(matchDayService.getMatchDay(id));

    }

    @GetMapping
    public ResponseEntity<List<MatchDayResponse>> getAll(){

        return ResponseEntity.ok(matchDayService.getAllMatchDays());

    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<MatchDayResponse> complete(
            @PathVariable Long id){

        return ResponseEntity.ok(
                matchDayService.completeMatchDay(id));

    }

    @PutMapping("/{matchDayId}/result")
    public ResponseEntity<MatchDayResponse> updateMatchResults(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMatchResultRequest request){
        MatchDayResponse response=matchDayService.updatematchResult(id,request);
        return ResponseEntity.ok(
                matchDayService.updatematchResult(id, request));
    }
}
