package com.example.desafio.api.controllers;

import com.example.desafio.api.models.dtos.BrandDto;
import com.example.desafio.api.services.ApiResponseService;
import com.example.desafio.api.services.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * Controller responsável por ATUALIZAR uma Marca
     *
     * @param id
     * @param brandDto
     * @return ResponseEntity<ApiResponseService>
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> updateBrand(@PathVariable Long id,@Valid @RequestBody BrandDto brandDto) {
        return brandService.updateBrand(id, brandDto);
    }

    /**
     * Controller responsável BUSCAR todas as marcas
     *
     * @return ResponseEntity<ApiResponseService>
     */
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> findAll() {
        return brandService.findAll();
    }

    /**
     * Controller responsável DELETAR uma marca e seus modelos associados
     *
     * @param id
     * @return ResponseEntity<ApiResponseService>
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> deleteBrand(@PathVariable Long id) {
        return brandService.deleteBrand(id);
    }
}
