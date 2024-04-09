package com.example.desafio.api.exceptions

class CarExceptions : RuntimeException {
    constructor() : super("Ocorreu um erro ao processar a operação com o carro.")
    constructor(message: String?) : super(message)
}