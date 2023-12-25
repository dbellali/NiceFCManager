package com.matawan.NiceFCManager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matawan.NiceFCManager.dto.PlayerCreateDto;
import com.matawan.NiceFCManager.dto.PlayerResponseDto;
import com.matawan.NiceFCManager.exception.PlayerNotFoundException;
import com.matawan.NiceFCManager.exception.TeamNotFoundException;
import com.matawan.NiceFCManager.model.Player;
import com.matawan.NiceFCManager.model.Team;
import com.matawan.NiceFCManager.repository.TeamRepository;

@Service
public class PlayerService {

    private static final String MSG_SUCCESS_DELETE = "Player removed";

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PlayerResponseDto addPlayer(Integer teamId, PlayerCreateDto playerDto) {
        Team team       = getTeamById(teamId);
        Player player   = this.modelMapper.map(playerDto, Player.class);
        team.addPlayers(player);
        return saveAndGetDto(team, player); 
    }

    public List<PlayerResponseDto> getAllPlayers(Integer teamId) {
        Team team       = getTeamById(teamId);
        return team.getPlayers().stream()
            .map(player -> this.modelMapper.map(player, PlayerResponseDto.class))
            .toList();
    }

    public PlayerResponseDto getPlayer(Integer teamId, Integer id) {
        Team team       = getTeamById(teamId);
        Player player   = getPlayerByIdFromTeam(team, id);
        return this.modelMapper.map(player, PlayerResponseDto.class);
    }

    public PlayerResponseDto updatePlayer(Integer teamId, Integer id, PlayerCreateDto playerDto) {
        Team team       = getTeamById(teamId);
        Player player   = getPlayerByIdFromTeam(team, id);
        this.modelMapper.map(playerDto, player);
        return saveAndGetDto(team, player); 
    }

    public String removePlayer(Integer teamId, Integer id) {
        Team team = getTeamById(teamId);
        if (!team.playerExistsById(id)) {
            throw new PlayerNotFoundException(teamId, id);
        }
        team.removePlayerById(id);
        this.teamRepository.save(team);
        return MSG_SUCCESS_DELETE;
    }

    private Team getTeamById(Integer id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }

    private Player getPlayerByIdFromTeam(Team team, Integer id) {
        return team.getPlayerById(id).orElseThrow(() -> new PlayerNotFoundException(team.getId(), id));
    }

    private PlayerResponseDto saveAndGetDto(Team team, Player player) {
        this.teamRepository.save(team);
        return this.modelMapper.map(player, PlayerResponseDto.class); 
    }


    
}
