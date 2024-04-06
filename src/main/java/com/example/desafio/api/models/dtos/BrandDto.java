package com.example.desafio.api.models.dtos;

import lombok.Data;

@Data
public class BrandDto {
    private Long id;
    private Boolean deleted;
    private String nameBrand;
}
