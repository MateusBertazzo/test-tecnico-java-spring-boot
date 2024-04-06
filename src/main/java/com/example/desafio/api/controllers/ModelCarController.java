package com.example.desafio.api.controllers;

import com.example.desafio.api.models.dtos.CarDto;
import com.example.desafio.api.services.ApiResponseService;
import com.example.desafio.api.services.ModelCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/model-car")
public class ModelCarController {

    private final ModelCarService modelCarService;

    @Autowired
    public ModelCarController(ModelCarService modelCarService) {
        this.modelCarService = modelCarService;
    }

    /**
     * Controller responsável por ATUALIZAR um modelo de carro
     *
     * @return ResponseEntity<ApiResponseService>
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> updateModelCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        return modelCarService.updateModelCar(id, carDto);
    }
}
