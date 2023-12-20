package com.matawan.NiceFCManager.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matawan.NiceFCManager.model.TeamEntity;
import com.matawan.NiceFCManager.repository.TeamRepository;

@Service
public class TeamService{
    
    @Autowired
    private TeamRepository teamRepository;

    public List<TeamEntity> getAllTeams() {
        
        return teamRepository.findAll();
    }

    public TeamEntity getTeamByID(Integer id) throws RuntimeException {
        
        Boolean teamExists = teamRepository.existsById(id);

        if(teamExists) {
            return teamRepository.findById(id).get();
        }

        throw new RuntimeException("Id not found");
    }

    public TeamEntity createTeam(TeamEntity team) throws RuntimeException{
        return teamRepository.save(team);
    }

}
