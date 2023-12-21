package com.matawan.NiceFCManager.exception;

import org.springframework.http.HttpStatus;

public class PlayerNotFoundException extends CustomHttpException {

    public PlayerNotFoundException(Integer id) {
        super(HttpStatus.BAD_REQUEST, "Player with id " + id + " not found");
    }

    public PlayerNotFoundException(Integer teamId, Integer id) {
        super(HttpStatus.BAD_REQUEST, "Player with id " + id + " not found in team with id " + teamId);
    }
    
}
