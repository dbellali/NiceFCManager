package com.matawan.NiceFCManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matawan.NiceFCManager.model.PlayerEntity;
import com.matawan.NiceFCManager.model.TeamEntity;
import com.matawan.NiceFCManager.repository.PlayerRepository;
import com.matawan.NiceFCManager.repository.TeamRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<PlayerEntity> getAllPlayers() {
        
        return playerRepository.findAll();
    }


    public PlayerEntity getPlayerByID(Integer id) throws RuntimeException {
        
        Boolean playerExists = playerRepository.existsById(id);

        if(playerExists) {
            return playerRepository.findById(id).get();
        }

        throw new RuntimeException("Id not found");
    }

    public PlayerEntity createPlayer(Integer teamId, PlayerEntity player) {

        TeamEntity team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + teamId));
        team.addPlayers(player);
        teamRepository.save(team);
        return playerRepository.save(player);

    }
}
