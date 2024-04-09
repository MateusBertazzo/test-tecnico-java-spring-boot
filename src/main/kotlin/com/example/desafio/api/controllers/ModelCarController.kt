package com.example.desafio.api.controllers

import com.example.desafio.api.models.dtos.ModelDto
import com.example.desafio.api.services.ApiResponseService
import com.example.desafio.api.services.ModelCarService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/model-car")
class ModelCarController @Autowired constructor(private val modelCarService: ModelCarService) {
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateModelCar(@PathVariable id: Long?,@Valid @RequestBody modelDto: ModelDto?): ResponseEntity<ApiResponseService> {
        return modelCarService.updateModelCar(id!!, modelDto!!)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get-all")
    fun allModelsCars(): ResponseEntity<ApiResponseService> {
        return modelCarService.allModelCars()
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteModelCar(@PathVariable id: Long?): ResponseEntity<ApiResponseService> {
        return modelCarService.deleteModelCar(id!!)
    }
}