package com.matawan.NiceFCManager.exception;

import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends CustomHttpException {

    public TeamNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "Team not found");
        
    }
    
}
