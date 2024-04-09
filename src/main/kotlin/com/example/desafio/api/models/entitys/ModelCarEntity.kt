package com.example.desafio.api.models.entitys

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "model_cars")
data class ModelCarEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var deleted: Boolean = false,

        @Column(name = "model_name", unique = true)
        var modelName: String? = null,

        @Column(name = "price_fip", precision = 12, scale = 3)
        var priceFip: BigDecimal? = null,

        @OneToMany(mappedBy = "modelId", cascade = [CascadeType.ALL], orphanRemoval = true)
        var cars: List<CarEntity>? = null,

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "brand_id")
        var brandId: BrandEntity? = null
)