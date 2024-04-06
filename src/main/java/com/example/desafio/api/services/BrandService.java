package com.example.desafio.api.services;

import com.example.desafio.api.exceptions.CarExceptions;
import com.example.desafio.api.models.dtos.BrandDto;
import com.example.desafio.api.models.dtos.CarDto;
import com.example.desafio.api.models.entitys.BrandEntity;
import com.example.desafio.api.models.repositorys.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Método responsável para buscar todas as marcas
     *
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> findAll() {
        try {

            List<BrandEntity> allBrands = brandRepository.findAll();

            // Crio uma lista de DTO que vou preencher com os valores do banco
            List<BrandDto> brandDtos = new ArrayList<>();

            for (BrandEntity brand : allBrands) {

                // Crio uma instancia de DTO seto os valores, logo depois adiciono eles na lista
                BrandDto brandDto = new BrandDto();
                brandDto.setId(brand.getId());
                brandDto.setDeleted(brand.getDeleted());
                brandDto.setNameBrand(brand.getNameBrand());

                brandDtos.add(brandDto);
            }

            return ApiResponseService.createSuccessResponse("Marcas encontradas com sucesso", brandDtos);
        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao buscar as marcas: " + e.getMessage());
        }
    }
}