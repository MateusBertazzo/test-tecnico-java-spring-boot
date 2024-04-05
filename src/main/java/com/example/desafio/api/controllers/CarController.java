package com.example.desafio.api.controllers;

import com.example.desafio.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Controller responsável por retornar todos os carros e o nome do modelo associado
     *
     * @return  List<Object[]>
     */
    @GetMapping("/all-cars")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findAllCarsAndModelName() {
        return carService.findAllCarsAndModelName();
    }
}
