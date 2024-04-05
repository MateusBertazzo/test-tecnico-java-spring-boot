package com.example.desafio.api.models.repositorys;

import com.example.desafio.api.models.entitys.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    // query que irá retornar todos os carros + o nome da marca que está associada a ele
    @Query("SELECT car.id as id, " +
            "car.timestampRegister as timestampRegister, " +
            "car.modelId.id as modelId, " +
            "model.name as modelName, " +
            "car.year as year, " +
            "car.fuel as fuel, " +
            "car.numDoors as numDoors, " +
            "car.color as color, " +
            "car.modelId.priceFip as priceFip " +
            "FROM CarEntity car " +
            "JOIN car.modelId model")
    List<CarModelProjection> findAllCarsAndModelName();
}
