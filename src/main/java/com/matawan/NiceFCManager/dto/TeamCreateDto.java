package com.matawan.NiceFCManager.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamCreateDto {

    private String name;
    private String acronym;
    private double budget;
}
