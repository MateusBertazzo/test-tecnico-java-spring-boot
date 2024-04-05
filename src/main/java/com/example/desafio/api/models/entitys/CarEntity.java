package com.example.desafio.api.models.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "timestamp_register")
    private LocalDateTime timestampRegister;

    private Integer year;

    private String fuel;

    @Column(name = "num_doors")
    private Integer numDoors;

    private String color;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelCarEntity modelId;
}
