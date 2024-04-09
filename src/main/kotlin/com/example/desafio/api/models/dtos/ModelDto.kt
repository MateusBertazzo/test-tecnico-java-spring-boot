package com.example.desafio.api.models.dtos

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

class ModelDto(

        @field:NotBlank(message = "O nome do modelo é obrigatório")
        val modelName: String? = null,

        @field:Min(value = 0, message = "O preço FIP deve ser maior que 0")
        @field:NotNull(message = "O preço FIP é obrigatório")
        val priceFip: BigDecimal? = null
)
