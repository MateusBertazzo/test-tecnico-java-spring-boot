package com.example.desafio.api.models.entitys

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "cars")
data class CarEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var deleted: Boolean = false,

        @Column(name = "timestamp_register")
        var timestampRegister: Long? = null,

        var year: Int? = null,

        var fuel: String? = null,

        @Column(name = "num_doors")
        var numDoors: Int? = null,

        var color: String? = null,

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "model_id")
        var modelId: ModelCarEntity? = null
)
