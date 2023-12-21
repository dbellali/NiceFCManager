package com.matawan.NiceFCManager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matawan.NiceFCManager.dto.PlayerCreateDto;
import com.matawan.NiceFCManager.dto.TeamCreateDto;
import com.matawan.NiceFCManager.dto.TeamResponseDto;
import com.matawan.NiceFCManager.exception.TeamNotFoundException;
import com.matawan.NiceFCManager.model.Player;
import com.matawan.NiceFCManager.model.Team;
import com.matawan.NiceFCManager.repository.TeamRepository;

@Service
public class TeamService{

    private static final String MSG_SUCCESS_DELETE = "Team deleted";
    
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TeamResponseDto> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(team -> this.modelMapper.map(team, TeamResponseDto.class))
                .toList();
    }

    public TeamResponseDto getTeam(Integer id){
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        return this.modelMapper.map(team, TeamResponseDto.class);
    }

    public TeamResponseDto createTeam(TeamCreateDto teamDto) {
        Team team = this.modelMapper.map(teamDto, Team.class);
        if (teamDto.getPlayers() != null) {
            List<Player> Players = convertPlayersDtoToPlayers(teamDto.getPlayers());
            team.setPlayers(Players);
        }
        
        return saveAndGetDto(team);
    }

    public TeamResponseDto updateTeam(Integer id, TeamCreateDto teamDto) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        this.modelMapper.map(teamDto, team);
        return saveAndGetDto(team);
    }

    public String deleteTeam(Integer id){
        if (! teamRepository.existsById(id)) {
            throw new TeamNotFoundException(id);
        }
        teamRepository.deleteById(id);
        return MSG_SUCCESS_DELETE;
    }

    private TeamResponseDto saveAndGetDto(Team team) {
        team = this.teamRepository.save(team);
        return this.modelMapper.map(team, TeamResponseDto.class);
    }

    private List<Player> convertPlayersDtoToPlayers(List<PlayerCreateDto> playersDto) {
        return playersDto.stream()
                .map(playerDto -> this.modelMapper.map(playerDto, Player.class))
                .toList();
    }

}
