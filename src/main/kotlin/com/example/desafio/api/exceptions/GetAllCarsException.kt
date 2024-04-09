package com.example.desafio.api.exceptions

class GetAllCarsException : RuntimeException {
    constructor() : super("Erro ao buscar carros")
    constructor(message: String?) : super(message)
}