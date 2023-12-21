package com.matawan.NiceFCManager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matawan.NiceFCManager.dto.TeamCreateDto;
import com.matawan.NiceFCManager.dto.TeamResponseDto;
import com.matawan.NiceFCManager.exception.TeamNotFoundException;
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
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        return this.modelMapper.map(team, TeamResponseDto.class);
    }

    public TeamResponseDto createTeam(TeamCreateDto teamDto) {
        Team team = this.modelMapper.map(teamDto, Team.class);
        return saveAndGetDto(team);
    }

    public TeamResponseDto updateTeam(Integer id, TeamCreateDto teamDto) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
        this.modelMapper.map(teamDto, team);
        return saveAndGetDto(team);
    }

    private TeamResponseDto saveAndGetDto(Team team) {
        team = this.teamRepository.save(team);
        return this.modelMapper.map(team, TeamResponseDto.class);
    }

    public String deleteTeam(Integer id){
        if (! teamRepository.existsById(id)) {
            throw new TeamNotFoundException();
        }
        teamRepository.deleteById(id);
        return MSG_SUCCESS_DELETE;
    }

}
