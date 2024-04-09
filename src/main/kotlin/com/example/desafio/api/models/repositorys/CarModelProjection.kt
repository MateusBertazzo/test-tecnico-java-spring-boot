package com.example.desafio.api.models.repositorys

import java.math.BigDecimal

// "Interface" serve para que o JPA consiga fazer a projeção dos dados e retornar no formato que desejo
interface CarModelProjection {
    val id: Long?
    val timestampRegister: Long?
    val modelId: Long?
    val modelName: String?
    val year: Int?
    val fuel: String?
    val numDoors: Int?
    val color: String?
    val priceFip: BigDecimal?
}