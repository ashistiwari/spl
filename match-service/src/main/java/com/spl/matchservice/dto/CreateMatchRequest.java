package com.spl.matchservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMatchRequest {


    @FutureOrPresent
    private LocalDate matchDate;



    @Min(1)
    @Max(50)
    private Integer totalOvers;

    @NotEmpty
    private Integer teamOneId;
    @NotEmpty
    private Integer teamTwoId;

}
