package com.spl.matchservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "player-service")
public interface PlayerServiceClient {

    @GetMapping("/api/players/{playerId}")
    Object getPlayer(@PathVariable Long playerId);
}
