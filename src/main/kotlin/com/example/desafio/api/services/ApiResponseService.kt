package com.example.desafio.api.services

import lombok.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

// Método responsavel por retornar uma resposta padronizada para as requisições facilitando para o cliente na manipulação dos dados retornados.
@Data
class ApiResponseService(var success: Boolean, var message: String, var response: Any?) {
    companion object {
        // Case Success
        @JvmStatic
        fun createSuccessResponse(message: String, response: Any?): ResponseEntity<ApiResponseService> {
            return ResponseEntity.ok(ApiResponseService(true, message, response))
        }

        // Case Error
        @JvmStatic
        fun createErrorResponse(message: String): ResponseEntity<ApiResponseService> {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseService(false, message, null))
        }
    }
}