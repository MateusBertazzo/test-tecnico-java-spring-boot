package com.example.desafio.api.exceptions;

public class CarExceptions extends RuntimeException {
    public CarExceptions() {
        super("Erro ao cadastrar um carro");
    }

    public CarExceptions(String message) {
        super(message);
    }
}
