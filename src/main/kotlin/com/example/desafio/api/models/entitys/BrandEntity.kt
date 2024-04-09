package com.example.desafio.api.models.entitys

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank


@Entity
@Table(name = "brands")
data class BrandEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0L,

        var deleted: Boolean = false,

        @Column(name = "name_brand")
        var nameBrand: String? = null,

        @OneToMany(mappedBy = "brandId", cascade = [CascadeType.ALL], orphanRemoval = true)
        var modelCars: List<ModelCarEntity>? = null
)