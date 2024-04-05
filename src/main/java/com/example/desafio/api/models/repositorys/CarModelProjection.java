package com.example.desafio.api.models.repositorys;

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
    Double getPriceFip();
}
