package com.matawan.NiceFCManager.exception;

import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends CustomHttpException {

    public TeamNotFoundException(Integer id) {
        super(HttpStatus.BAD_REQUEST, "Team with id " + id + " not found");
        
    }
    
}
