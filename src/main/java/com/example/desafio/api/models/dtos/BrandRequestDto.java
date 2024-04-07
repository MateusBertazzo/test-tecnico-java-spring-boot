package com.example.desafio.api.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BrandRequestDto {
    @NotBlank(message = "O nome da marca é obrigatório")
    private String nameBrand;
}
