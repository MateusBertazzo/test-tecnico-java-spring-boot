package com.example.desafio.api.models.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "model_cars")
@Data
public class ModelCarEntity extends BaseEntity {

    private String name;

    @Column(name = "price_fip")
    private Double priceFip;

    @OneToMany(mappedBy = "modelId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarEntity> cars;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brandId;
}