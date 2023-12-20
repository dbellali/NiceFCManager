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
import org.springframework.web.bind.annotation.RestController;

import com.matawan.NiceFCManager.model.PlayerEntity;
import com.matawan.NiceFCManager.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
    
    @Autowired PlayerService playerService;


    @GetMapping("")
   public List<PlayerEntity> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPlayerByID(@PathVariable Integer id) {
        
        try{
            PlayerEntity playerEntity = playerService.getPlayerByID(id);
            ResponseEntity<PlayerEntity> responseEntityPlayer= new ResponseEntity<PlayerEntity>(playerEntity, HttpStatusCode.valueOf(200));
            return responseEntityPlayer;
        } catch(Exception e) {
            String message = e.getMessage();
            ResponseEntity<String> responseEntityErrorMessage = new ResponseEntity<String>(message, HttpStatusCode.valueOf(404));
            return responseEntityErrorMessage;
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createPlayer(@PathVariable Integer id, @RequestBody PlayerEntity player) {

        try {
            return new ResponseEntity<>(this.playerService.createPlayer(id,player), HttpStatusCode.valueOf(201));
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Internal Error", HttpStatusCode.valueOf(500));
        }
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
