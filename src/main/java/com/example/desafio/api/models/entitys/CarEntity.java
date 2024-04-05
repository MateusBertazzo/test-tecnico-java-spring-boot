package com.example.desafio.api.models.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {

    @Column(name = "timestamp_register")
    private Long timestampRegister;

    private Integer year;

    private String fuel;

    @Column(name = "num_doors")
    private Integer numDoors;

    private String color;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelCarEntity modelId;

    public CarEntity(Long timestampRegister, Integer year, String fuel, Integer numDoors, String color, ModelCarEntity modelId) {
        this.timestampRegister = timestampRegister;
        this.year = year;
        this.fuel = fuel;
        this.numDoors = numDoors;
        this.color = color;
        this.modelId = modelId;
    }

    public CarEntity() {
    }
}
