package com.example.desafio.api.models.dtos;

import com.example.desafio.api.models.entitys.BrandEntity;
import com.example.desafio.api.models.entitys.CarEntity;
import com.example.desafio.api.models.entitys.ModelCarEntity;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CarDto {

    private String brandName;
    private String modelName;

    private Integer year;
    private String fuel;
    private Integer numDoors;
    private String color;
    private Double priceFip;

    public BrandEntity toBrand() {
        BrandEntity marcaEntity = new BrandEntity();
        marcaEntity.setNameBrand(brandName);
        return marcaEntity;
    }

    public ModelCarEntity toModelCar(BrandEntity brand) {
        ModelCarEntity modelCarEntity = new ModelCarEntity();
        modelCarEntity.setName(modelName);
        modelCarEntity.setPriceFip(priceFip);
        modelCarEntity.setBrandId(brand);
        return modelCarEntity;
    }

    public CarEntity toCar(ModelCarEntity modelCar) {
        CarEntity carEntity = new CarEntity();

        carEntity.setColor(color);
        carEntity.setYear(year);
        carEntity.setFuel(fuel);
        carEntity.setTimestampRegister(LocalDateTime.now());
        carEntity.setModelId(modelCar);

        return carEntity;
    }
}
