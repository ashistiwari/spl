package com.spl.playerservice.mapper;

import com.spl.playerservice.dto.CreatePlayersRequest;
import com.spl.playerservice.dto.PlayersResponse;
import com.spl.playerservice.entity.Players;
import org.springframework.stereotype.Component;

@Component
public class PlayersMapper {

    private PlayersMapper() {
    }

    public static Players toEntity(CreatePlayersRequest request) {

        return Players.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .age(request.getAge())
                .role(request.getRole())
                .battingStyle(request.getBattingStyle())
                .bowlingStyle(request.getBowlingStyle())
                .active(true)
                .build();
    }

    public static PlayersResponse toResponse(Players player) {

        return PlayersResponse.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .email(player.getEmail())
                .mobile(player.getMobile())
                .age(player.getAge())
                .role(player.getRole())
                .battingStyle(player.getBattingStyle())
                .bowlingStyle(player.getBowlingStyle())
                .totalMatches(player.getTotalMatches())
                .totalRuns(player.getTotalRuns())
                .totalWickets(player.getTotalWickets())
                .active(player.getActive())
                .build();
    }


}
