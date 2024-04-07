package com.example.desafio.api.services;

import com.example.desafio.api.exceptions.CarExceptions;
import com.example.desafio.api.models.dtos.BrandDto;
import com.example.desafio.api.models.dtos.BrandRequestDto;
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
     * @param brandDto
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> updateBrand(Long id, BrandRequestDto brandRequestDto) {
        try {

            BrandEntity brandEntity = brandRepository.findById(id).orElseThrow(() -> new CarExceptions("Marca não encontrada"));

            brandEntity.setNameBrand(brandRequestDto.getNameBrand());

            brandRepository.save(brandEntity);

            return ApiResponseService.createSuccessResponse("Marca atualizada com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao atualizar a marca: " + e.getMessage());
        }
    }

    /**
     * Método responsável para BUSCAR todas as marcas
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

    /**
     * Método responsável por DELETAR uma marca e seus modelos associados
     *
     * @param id
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> deleteBrand(Long id) {
        try {

            BrandEntity brandEntity = brandRepository.findById(id).orElseThrow(() -> new CarExceptions("Marca não encontrada"));

            brandEntity.setDeleted(true);

            // Deleta todos os modelos associados a marca
            brandEntity.getModelCars().forEach(modelCar -> modelCar.setDeleted(true));

            // Deleta todos os carros associados aos modelos deletados
            brandEntity.getModelCars().forEach(modelCar -> modelCar.getCars().forEach(car -> car.setDeleted(true)));

            brandRepository.save(brandEntity);

            return ApiResponseService.createSuccessResponse("Marca deletada com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao deletar a marca: " + e.getMessage());
        }
    }
}