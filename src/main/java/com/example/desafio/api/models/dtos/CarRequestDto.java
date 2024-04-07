package com.example.desafio.api.models.dtos;

import com.example.desafio.api.exceptions.CarExceptions;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Calendar;

@Data
public class CarRequestDto {

    @Min(value = 1900, message = "O ano deve ser maior que 1900")
    @NotNull(message = "O ano é obrigatório")
    private Integer year;

    @NotBlank(message = "O combustível é obrigatório")
    private String fuel;

    @Min(value = 2, message = "O número de portas deve igual ou maior que 2")
    @NotNull(message = "O número de portas é obrigatório")
    private Integer numDoors;

    @NotBlank(message = "A cor é obrigatória")
    private String color;
}
