package com.matawan.NiceFCManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.matawan.NiceFCManager.dto.ErrorMessage;
import com.matawan.NiceFCManager.exception.CustomHttpException;

@ControllerAdvice
public class AbstractController {

    protected ResponseEntity<?> successResponse(Object response) {
        return ResponseEntity.ok().body(response);
    }

    protected ResponseEntity<?> successResponse(HttpStatus httpStatus, Object response) {
        return response(httpStatus, response);
    }

    protected ResponseEntity<?> errorResponse() {
        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    protected ResponseEntity<?> errorResponse(CustomHttpException e) {
        return errorResponse(e.getHttpStatus(), e.getMessage());
    }

    protected ResponseEntity<?> errorResponse(HttpStatus httpStatus, Exception e) {
        return errorResponse(httpStatus, e.getMessage());
    }

    protected ResponseEntity<?> errorResponse(HttpStatus httpStatus, String message) {
        return response(httpStatus, new ErrorMessage(httpStatus.value(), message));
    }

    protected ResponseEntity<?> response(HttpStatus httpStatus, Object response) {
        return ResponseEntity
                .status(httpStatus)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        return errorResponse(HttpStatus.BAD_REQUEST, "Validation failed: " + ex.getFieldError().getDefaultMessage());
    }
}