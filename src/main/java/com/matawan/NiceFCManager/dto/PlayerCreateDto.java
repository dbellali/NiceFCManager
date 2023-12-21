package com.matawan.NiceFCManager.dto;

import java.util.List;

import com.matawan.NiceFCManager.enumeration.PlayerPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerCreateDto {
    private String name;
    private List<PlayerPosition> position;
}
