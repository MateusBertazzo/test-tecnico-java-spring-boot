package com.example.desafio.api.controllers;

import com.example.desafio.api.models.dtos.ModelDto;
import com.example.desafio.api.services.ApiResponseService;
import com.example.desafio.api.services.ModelCarService;
import jakarta.validation.Valid;
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
     * @param id
     * @return ResponseEntity<ApiResponseService>
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> updateModelCar(@PathVariable Long id, @Valid @RequestBody ModelDto modelDto) {
        return modelCarService.updateModelCar(id, modelDto);
    }

    /**
     * Controller responsável por retornar todos os modelos
     *
     * @return ResponseEntity<ApiResponseService>
     */
    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> getAllModelsCars() {
        return modelCarService.getAllModelCars();
    }

    /**
     * Controller responsável por deletar um modelo
     *
     * @return ResponseEntity<ApiResponseService>
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> deleteModelCar(@PathVariable Long id) {
        return modelCarService.deleteModelCar(id);
    }
}
