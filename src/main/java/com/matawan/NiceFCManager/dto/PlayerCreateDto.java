package com.matawan.NiceFCManager.dto;

import java.util.List;

import com.matawan.NiceFCManager.enumeration.PlayerPosition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerCreateDto {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "position cannot be empty")
    private List<PlayerPosition> position;
}
