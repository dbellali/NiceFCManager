package com.matawan.NiceFCManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.matawan.NiceFCManager.model.TeamEntity;
import com.matawan.NiceFCManager.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {
    
    @Autowired
    private TeamService teamService;

    @GetMapping("")
    public List<TeamEntity> getAllteams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamByID(@PathVariable Integer id) {
        
        try{
            TeamEntity teamEntity = teamService.getTeamByID(id);
            ResponseEntity<TeamEntity> responseEntityTeam = new ResponseEntity<TeamEntity>(teamEntity, HttpStatusCode.valueOf(200));
            return responseEntityTeam;
        } catch(Exception e) {
            String message = e.getMessage();
            ResponseEntity<String> responseEntityErrorMessage = new ResponseEntity<String>(message, HttpStatusCode.valueOf(404));
            return responseEntityErrorMessage;
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createTeam(@RequestBody TeamEntity team) {

        try {
            return new ResponseEntity<>(this.teamService.createTeam(team), HttpStatusCode.valueOf(201));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Internal Error", HttpStatusCode.valueOf(500));
        }
    }

    @PutMapping("{id}")
    public String updateTeamByID() {
        return "team updated by id";
    }

    @DeleteMapping("{id}")
    public String deleteTeamrByID() {
        return "team deleted by id";
    }
}
