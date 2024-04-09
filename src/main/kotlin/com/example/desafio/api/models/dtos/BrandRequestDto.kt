package com.example.desafio.api.models.dtos

import jakarta.validation.constraints.NotBlank

data class BrandRequestDto(

        @field:NotBlank(message = "O nome da marca é obrigatório")
        var nameBrand:  String? = null
)
