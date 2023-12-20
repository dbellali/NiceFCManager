package com.matawan.NiceFCManager.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player")
public class PlayerController {
    
    @GetMapping("")
    public String getAllPlayers() {
        return "hello";
    }

    @GetMapping("{id}")
    public String getPlayerByID() {
        return "i'm the player";
    }

    @PostMapping("")
    public String addPlayer() {
        return "player added";
    }

    @PutMapping("{id}")
    public String updatePlayerByID() {
        return "player updated by id";
    }

    @DeleteMapping("{id}")
    public String deletePlayerByID() {
        return "player deleted by id";
    }


}
