package com.example.desafio.api.controllers;

import com.example.desafio.api.models.dtos.BrandDto;
import com.example.desafio.api.models.dtos.BrandRequestDto;
import com.example.desafio.api.services.ApiResponseService;
import com.example.desafio.api.services.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> updateBrand(@PathVariable Long id, @Valid @RequestBody BrandRequestDto brandRequestDto) {
        return brandService.updateBrand(id, brandRequestDto);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> findAll() {
        return brandService.findAll();
    }

//    @Operation(description = "Operação para Deletar uma marca")
//    @ApiResponses(
//
//            value = {
//                    @ApiResponse(responseCode = "200", description = "Marca deletada com sucesso"),
//                    @ApiResponse(responseCode = "400", description = "Erro ao deletar a marca"),
//                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
//            }
//    )
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApiResponseService> deleteBrand(@PathVariable Long id) {
        return brandService.deleteBrand(id);
    }
}
