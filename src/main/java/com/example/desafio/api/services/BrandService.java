package com.example.desafio.api.services;

import com.example.desafio.api.exceptions.CarExceptions;
import com.example.desafio.api.models.dtos.CarDto;
import com.example.desafio.api.models.entitys.BrandEntity;
import com.example.desafio.api.models.repositorys.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    /**
     * Método responsável por ATUALIZAR uma Marca
     *
     * @param id
     * @param carDto
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> updateBrand(Long id, CarDto carDto) {
        try {

            BrandEntity brandEntity = brandRepository.findById(id).orElseThrow(() -> new CarExceptions("Marca não encontrada"));

            brandEntity.setNameBrand(carDto.getNameBrand());

            brandRepository.save(brandEntity);

            return ApiResponseService.createSuccessResponse("Marca atualizada com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao atualizar a marca: " + e.getMessage());
        }
    }
}
