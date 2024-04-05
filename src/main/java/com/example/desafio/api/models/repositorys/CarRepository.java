package com.example.desafio.api.models.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<CarRepository, Long> {

    // query que irá retornar todos os carros + o nome da marca que está associada a ele
    @Query("SELECT car, model.name FROM CarEntity car JOIN car.modelId model")
    List<Object[]> findAllCarsWithBrandData();

}
