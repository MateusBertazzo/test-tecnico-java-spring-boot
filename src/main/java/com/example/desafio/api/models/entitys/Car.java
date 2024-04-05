package com.example.desafio.api.models.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp_register")
    private LocalDateTime timestampRegister;

    private Integer year;

    private String fuel;

    @Column(name = "num_doors")
    private Integer numDoors;

    private String color;
}
