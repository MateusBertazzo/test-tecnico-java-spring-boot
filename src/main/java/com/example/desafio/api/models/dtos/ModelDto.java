package com.example.desafio.api.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ModelDto {

    @NotBlank(message = "O nome do modelo é obrigatório")
    private String modelName;

    @Min(value = 0, message = "O preço FIP deve ser maior que 0")
    @NotNull(message = "O preço FIP é obrigatório")
    private BigDecimal priceFip;
}
