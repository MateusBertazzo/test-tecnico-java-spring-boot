package com.example.desafio.api.controllers;

import com.example.desafio.api.models.dtos.CarDto;
import com.example.desafio.api.services.ApiResponseService;
import com.example.desafio.api.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Controller responsável por REGISTRAR um carro
     *
     * @param   carDto
     * @return  ResponseEntity<ApiResponseService>
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponseService> registerCar(@RequestBody CarDto carDto) {
        return carService.registerCar(carDto);
    }

    /**
     * Controller responsável por retornar TODOS os carros e o nome do modelo associado
     *
     * @return  ResponseEntity<ApiResponseService>
     */
    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> findAllCarsAndModelName() {
        return carService.findAllCarsAndModelName();
    }

    /**
     * Controller responsável por retornar ATUALIZAR um carro
     *
     * @return  ResponseEntity<ApiResponseService>
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        return carService.updateCar(id, carDto);
    }
}