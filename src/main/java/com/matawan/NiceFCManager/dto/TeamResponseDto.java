package com.matawan.NiceFCManager.dto;
import java.util.List;

import com.matawan.NiceFCManager.model.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamResponseDto {
    private Integer id;
    private String name;
    private String acronym;
    private double budget;
    private List<Player> players;
}
