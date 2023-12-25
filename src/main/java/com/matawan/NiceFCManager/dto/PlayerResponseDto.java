package com.matawan.NiceFCManager.dto;

import java.util.List;

import com.matawan.NiceFCManager.enumeration.PlayerPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerResponseDto {
    private Integer id;
    private String name;
    private List<PlayerPosition> position;
}
