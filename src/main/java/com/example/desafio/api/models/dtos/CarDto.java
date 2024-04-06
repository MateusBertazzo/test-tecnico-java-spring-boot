package com.example.desafio.api.models.dtos;

import com.example.desafio.api.models.entitys.BrandEntity;
import com.example.desafio.api.models.entitys.CarEntity;
import com.example.desafio.api.models.entitys.ModelCarEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class CarDto {

    private String nameBrand;
    private String modelName;
    private Integer year;
    private String fuel;
    private Integer numDoors;
    private String color;
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
