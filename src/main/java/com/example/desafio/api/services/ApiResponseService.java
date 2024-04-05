package com.example.desafio.api.services;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    public ApiResponseService(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Case Sucess
    public static ResponseEntity<ApiResponseService> createSuccessResponse(Boolean Success, String message, Object response) {
        return ResponseEntity.ok(new ApiResponseService(true, "Sucesso", response));
    }

    // Case Error
    public static ResponseEntity<ApiResponseService> createErrorResponse(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseService(false, message));
    }
}
