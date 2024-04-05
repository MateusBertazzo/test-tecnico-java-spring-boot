package com.example.desafio.api.exceptions;

public class PostRegisterCarException extends RuntimeException {
    public PostRegisterCarException() {
        super("Erro ao cadastrar um carro");
    }

    public PostRegisterCarException(String message) {
        super(message);
    }
}
