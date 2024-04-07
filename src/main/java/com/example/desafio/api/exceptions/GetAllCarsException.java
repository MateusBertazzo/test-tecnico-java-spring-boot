package com.example.desafio.api.exceptions;

public class GetAllCarsException extends RuntimeException {
    public GetAllCarsException() {
        super("Erro ao buscar carros");
    }
    public GetAllCarsException(String message) {
        super(message);
    }
}
