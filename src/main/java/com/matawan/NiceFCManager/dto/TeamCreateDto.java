package com.matawan.NiceFCManager.dto;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamCreateDto {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "acronym cannot be empty")
    private String acronym;

    @NotNull(message = "budget cannot be null")
    private double budget;

    @Valid
    private List<PlayerCreateDto> players;
}
