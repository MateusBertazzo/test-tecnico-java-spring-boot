package com.example.desafio.api.services;

import com.example.desafio.api.models.repositorys.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Método responsável por retornar todos os carros e o nome do modelo associado
     *
     * @return  List<Object[]>
     */
    public List<Object[]> findAllCarsAndModelName() {
        return carRepository.findAllCarsWithBrandData();
    }
}
