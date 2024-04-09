package com.example.desafio.api.controllers

import com.example.desafio.api.models.dtos.CarAndModelAndBrandDto
import com.example.desafio.api.models.dtos.CarRequestDto
import com.example.desafio.api.services.ApiResponseService
import com.example.desafio.api.services.CarService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/car")
class CarController @Autowired constructor(private val carService: CarService) {
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun registerCar(@Valid @RequestBody carAndModelAndBrandDto: CarAndModelAndBrandDto?): ResponseEntity<ApiResponseService> {
        return carService.registerCar(carAndModelAndBrandDto!!)
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    fun findAllCarsAndModelName(): ResponseEntity<ApiResponseService> {
        return carService.findAllCarsAndModelName()
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCar(@PathVariable id: Long?,@Valid @RequestBody carRequestDto: CarRequestDto?): ResponseEntity<ApiResponseService> {
        return carService.updateCar(id!!, carRequestDto!!)
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteCar(@PathVariable id: Long?): ResponseEntity<ApiResponseService> {
        return carService.deleteCar(id!!)
    }
}