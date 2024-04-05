package com.example.desafio.api.adviceControllerExeception;

import com.example.desafio.api.exceptions.GetAllCarsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionsAdvices {

    @ExceptionHandler(GetAllCarsException.class)
    public ResponseEntity<String> getAllCarsExceptionHandler(GetAllCarsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
