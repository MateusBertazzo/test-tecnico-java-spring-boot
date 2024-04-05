package com.example.desafio.api.models.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "model_cars")
@Data
public class ModelCarEntity extends BaseEntity {

    private String name;

    @Column(name = "price_fip", precision = 8, scale = 3)
    private BigDecimal priceFip;

    @OneToMany(mappedBy = "modelId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarEntity> cars;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brandId;

    public ModelCarEntity(String name, BigDecimal priceFip, List<CarEntity> cars, BrandEntity brandId) {
        this.name = name;
        this.priceFip = priceFip;
        this.cars = cars;
        this.brandId = brandId;
    }

    public ModelCarEntity() {
    }
}
