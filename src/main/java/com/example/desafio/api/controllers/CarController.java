package com.example.desafio.api.controllers;

import com.example.desafio.api.models.dtos.CarAndModelAndBrandDto;
import com.example.desafio.api.models.dtos.CarRequestDto;
import com.example.desafio.api.services.ApiResponseService;
import com.example.desafio.api.services.CarService;
import jakarta.validation.Valid;
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
     * @param   carAndModelAndBrandDto
     * @return  ResponseEntity<ApiResponseService>
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponseService> registerCar(@Valid @RequestBody CarAndModelAndBrandDto carAndModelAndBrandDto) {
        return carService.registerCar(carAndModelAndBrandDto);
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
    public ResponseEntity<ApiResponseService> updateCar(@PathVariable Long id,@Valid @RequestBody CarRequestDto carRequestDto) {
        return carService.updateCar(id, carRequestDto);
    }

    /**
     * Controller responsável por DELETAR um carro
     *
     * @return  ResponseEntity<ApiResponseService>
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> deleteCar(@PathVariable Long id) {
        return carService.deleteCar(id);
    }
}
