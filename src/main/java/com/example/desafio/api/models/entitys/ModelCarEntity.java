package com.example.desafio.api.models.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "model_cars")
@Data
public class ModelCarEntity extends BaseEntity {

    private String modelName;


    @Column(name = "price_fip", precision = 12, scale = 3)
    private BigDecimal priceFip;

    @OneToMany(mappedBy = "modelId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarEntity> cars;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brandId;

    public ModelCarEntity(String modelName, BigDecimal priceFip, List<CarEntity> cars, BrandEntity brandId) {
        this.modelName = modelName;
        this.priceFip = priceFip;
        this.cars = cars;
        this.brandId = brandId;
    }

    public ModelCarEntity() {
    }
}
