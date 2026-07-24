package com.spl.playerservice.dto;

import com.spl.playerservice.enums.BattingStyle;
import com.spl.playerservice.enums.BowlingStyle;
import com.spl.playerservice.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayersRequest {
    @NotBlank
    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String mobile;

    @Min(10)
    @Max(80)
    private Integer age;

    private Role role;

    private BattingStyle battingStyle;

    private BowlingStyle bowlingStyle;


}
