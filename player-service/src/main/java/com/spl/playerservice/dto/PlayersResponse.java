package com.spl.playerservice.dto;

import com.spl.playerservice.enums.BattingStyle;
import com.spl.playerservice.enums.BowlingStyle;
import com.spl.playerservice.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayersResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String mobile;
    private Role role;
    private BattingStyle battingStyle;
    private BowlingStyle bowlingStyle;
    private Integer totalRuns;
    private Integer totalMatches;
    private Integer totalWickets;
    private Boolean active;
}
