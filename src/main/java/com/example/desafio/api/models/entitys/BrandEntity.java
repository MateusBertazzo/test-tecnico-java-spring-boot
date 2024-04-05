package com.example.desafio.api.models.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    @Column(name = "name_brand")
    private String nameBrand;

    @OneToMany(mappedBy = "brandId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelCarEntity> modelCars;
}
