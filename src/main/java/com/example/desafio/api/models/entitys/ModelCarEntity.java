package com.example.desafio.api.models.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "model_cars")
@Data
public class ModelCarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "price_fip")
    private Double priceFip;

    @OneToMany(mappedBy = "modelId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarEntity> cars;
}
