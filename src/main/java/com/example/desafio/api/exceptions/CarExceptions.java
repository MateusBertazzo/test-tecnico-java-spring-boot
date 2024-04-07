package com.example.desafio.api.exceptions;

public class CarExceptions extends RuntimeException {
    public CarExceptions() {
        super("Ocorreu um erro ao processar a operação com o carro.");
    }

    public CarExceptions(String message) {
        super(message);
    }
}
