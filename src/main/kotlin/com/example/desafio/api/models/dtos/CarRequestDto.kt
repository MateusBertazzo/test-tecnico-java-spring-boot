package com.example.desafio.api.models.dtos

import com.example.desafio.api.models.customValidation.MaxYear
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class CarRequestDto(

        @MaxYear
        @field:Min(value = 1900, message = "O ano deve ser maior que 1900")
        @field:NotNull(message = "O ano é obrigatório")
        var year: Int? = null,

        @field:NotBlank(message = "O combustível é obrigatório")
        var fuel: String? = null,

        @field:Min(value = 2, message = "O número de portas deve igual ou maior que 2")
        @field:NotNull(message = "O número de portas é obrigatório")
        var numDoors: Int? = null,

        @field:NotBlank(message = "A cor é obrigatória")
        var color: String? = null

)
