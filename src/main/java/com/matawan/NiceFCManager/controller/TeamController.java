package com.matawan.NiceFCManager.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {
    
    @GetMapping("")
    public String getAllteams() {
        return "hello team";
    }

    @GetMapping("{id}")
    public String getTeamByID() {
        return "i'm the team";
    }

    @PostMapping("")
    public String addTeam() {
        return "team added";
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
