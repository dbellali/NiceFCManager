package com.matawan.NiceFCManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matawan.NiceFCManager.dto.PlayerCreateDto;
import com.matawan.NiceFCManager.exception.PlayerNotFoundException;
import com.matawan.NiceFCManager.exception.TeamNotFoundException;
import com.matawan.NiceFCManager.service.PlayerService;


@RestController
@RequestMapping("/team/{teamId}/player")
public class PlayerController extends AbstractController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("")
    public ResponseEntity<?> addPlayer(@PathVariable Integer teamId, @RequestBody PlayerCreateDto player) {
        ResponseEntity<?> response;
        try {
            response = this.successResponse(HttpStatus.CREATED, this.playerService.addPlayer(teamId, player));
        } catch (TeamNotFoundException e) {
            response = this.errorResponse(e);
        } catch (RuntimeException e) {
            response = this.errorResponse();
        }
        return response;
    }

    @GetMapping("")
    public ResponseEntity<?> getPlayers(@PathVariable Integer teamId) {
        ResponseEntity<?> response;
        try {
            response = this.successResponse(this.playerService.getAllPlayers(teamId));
        } catch (TeamNotFoundException e) {
            response = this.errorResponse(e);
        }  catch (RuntimeException e) {
            response = this.errorResponse();
        }
        return response;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPlayer(@PathVariable Integer teamId, @PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            response = this.successResponse(this.playerService.getPlayer(teamId, id));
        } catch (TeamNotFoundException | PlayerNotFoundException e) {
            response = this.errorResponse(e);
        }  catch (RuntimeException e) {
            response = this.errorResponse();
        }
        return response;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updatePlayer(
        @PathVariable Integer teamId, 
        @PathVariable Integer id,
        @RequestBody PlayerCreateDto player) {

        ResponseEntity<?> response;
        try {
            response = this.successResponse(HttpStatus.CREATED, this.playerService.updatePlayer(teamId, id, player));
        } catch (TeamNotFoundException | PlayerNotFoundException e) {
            response = this.errorResponse(e);
        }  catch (RuntimeException e) {
            response = this.errorResponse();
        }
        return response;    
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removePlayer(@PathVariable Integer teamId, @PathVariable Integer id) {
        ResponseEntity<?> response;
        try {
            response = this.successResponse(HttpStatus.CREATED, this.playerService.removePlayer(teamId, id));
        } catch (TeamNotFoundException | PlayerNotFoundException e) {
            response = this.errorResponse(e);
        }  catch (RuntimeException e) {
            System.out.println(e.getMessage());
            response = this.errorResponse();
        }
        return response;    
    }

}
