package com.example.desafio.api.models.dtos;

import com.example.desafio.api.models.entitys.BrandEntity;
import com.example.desafio.api.models.entitys.CarEntity;
import com.example.desafio.api.models.entitys.ModelCarEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class CarDto {

    @NotBlank(message = "O nome da marca é obrigatório")
    private String nameBrand;

    @NotBlank(message = "O nome do modelo é obrigatório")
    private String modelName;

    @NotNull(message = "O ano é obrigatório")
    private Integer year;

    @NotBlank(message = "O combustível é obrigatório")
    private String fuel;

    @NotNull(message = "O número de portas é obrigatório")
    private Integer numDoors;

    @NotBlank(message = "A cor é obrigatória")
    private String color;

    @NotNull(message = "O preço FIP é obrigatório")
    private BigDecimal priceFip;

    // Converte um DTO para uma entidade Brand
    public BrandEntity toBrand() {
        BrandEntity marcaEntity = new BrandEntity();

        marcaEntity.setNameBrand(nameBrand);

        return marcaEntity;
    }

    // Converte um DTO para uma entidade ModelCar
    public ModelCarEntity toModelCar(BrandEntity brand) {
        ModelCarEntity modelCarEntity = new ModelCarEntity();

        modelCarEntity.setModelName(modelName);
        modelCarEntity.setPriceFip(priceFip);
        modelCarEntity.setBrandId(brand);

        return modelCarEntity;
    }

    //Converte um DTO para uma entidade Car
    public CarEntity toCar(ModelCarEntity modelCar) {
        CarEntity carEntity = new CarEntity();

        carEntity.setColor(color);
        carEntity.setYear(year);
        carEntity.setFuel(fuel);
        carEntity.setNumDoors(numDoors);

        // Convertendo LocalDateTime para timesTamp Unix
        carEntity.setTimestampRegister(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        carEntity.setModelId(modelCar);

        return carEntity;
    }
}
