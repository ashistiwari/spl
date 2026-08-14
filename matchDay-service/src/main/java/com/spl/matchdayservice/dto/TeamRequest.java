package com.spl.matchdayservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  TeamRequest {
    @NotEmpty
    private List<Long> playerIds;
    @NotNull
    private Long captainId;

}
