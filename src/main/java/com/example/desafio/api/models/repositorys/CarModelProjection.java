package com.example.desafio.api.models.repositorys;

import java.math.BigDecimal;

// Interface serve para que o JPA consiga fazer a projeção dos dados e retornar no formato que desejo
public interface CarModelProjection {
    Long getId();
    Long getTimestampRegister();
    Long getModelId();
    String getModelName();
    Integer getYear();
    String getFuel();
    Integer getNumDoors();
    String getColor();
    BigDecimal getPriceFip();
}
