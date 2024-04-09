package com.example.desafio.api.controllers

import com.example.desafio.api.models.dtos.BrandRequestDto
import com.example.desafio.api.services.ApiResponseService
import com.example.desafio.api.services.BrandService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/brand")
class BrandController @Autowired constructor(private val brandService: BrandService) {
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateBrand(@PathVariable id: Long?,@Valid @RequestBody brandRequestDto: BrandRequestDto?): ResponseEntity<ApiResponseService> {
        return brandService.updateBrand(id!!, brandRequestDto!!)
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): ResponseEntity<ApiResponseService> {
        return brandService.findAll()
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteBrand(@PathVariable id: Long?): ResponseEntity<ApiResponseService> {
        return brandService.deleteBrand(id!!)
    }
}