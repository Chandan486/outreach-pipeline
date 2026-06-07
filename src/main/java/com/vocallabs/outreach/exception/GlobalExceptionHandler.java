package com.vocallabs.outreach.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {

        return ResponseEntity
                .internalServerError()
                .body("Error occurred: " + e.getMessage());
    }
}