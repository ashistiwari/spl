package com.spl.matchservice.dto;

import com.spl.commonlibrary.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Component
public class MatchValidator {

    public void validate(CreateMatchRequest request){
        if(request.getMatchDate().isBefore(LocalDate.now())){
            throw new BadRequestException("Match date cannot be in the past");
        }

        if(request.getTeamOne().getPlayerids().size()<2){
            throw new BadRequestException("Team 1 players must have at least 2 players");
        }
        if(request.getTeamTwo().getPlayerids().size()<2){
            throw new BadRequestException("Team 2 players must have at least 2 players");
        }
        Set<Long> teamOnePlayers =
                new HashSet<>(request.getTeamOne().getPlayerids());

        if(teamOnePlayers.size() !=
                request.getTeamOne().getPlayerids().size()){

            throw new BadRequestException(
                    "Duplicate players in Team One");

        }

        Set<Long> teamTwoPlayers =
                new HashSet<>(request.getTeamTwo().getPlayerids());

        if(teamTwoPlayers.size() !=
                request.getTeamTwo().getPlayerids().size()){

            throw new BadRequestException(
                    "Duplicate players in Team Two");

        }
        Set<Long> allPlayers =
                new HashSet<>(request.getTeamOne().getPlayerids());

        for(Long id : request.getTeamTwo().getPlayerids()){

            if(!allPlayers.add(id)){

                throw new BadRequestException(
                        "Player cannot be present in both teams");

            }

        }
        if(!request.getTeamOne()
                .getPlayerids()
                .contains(request.getTeamOne().getCaptainId())){

            throw new BadRequestException(
                    "Captain should belong to Team One");

        }

    }
}
