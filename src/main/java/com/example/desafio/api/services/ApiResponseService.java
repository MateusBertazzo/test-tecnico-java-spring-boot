package com.example.desafio.api.services;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ApiResponseService {

    private boolean success;

    private String message;

    private Object response;

    public ApiResponseService(boolean success, String message, Object response) {
        this.success = success;
        this.message = message;
        this.response = response;
    }


    // Case Success
    public static ResponseEntity<ApiResponseService> createSuccessResponse(String message, Object response) {
        return ResponseEntity.ok(new ApiResponseService(true, message, response));
    }

    // Case Error
    public static ResponseEntity<ApiResponseService> createErrorResponse(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseService(false, message, null));
    }
}
