package com.example.desafio.api.services;

import com.example.desafio.api.exceptions.CarExceptions;
import com.example.desafio.api.models.dtos.CarDto;
import com.example.desafio.api.models.entitys.ModelCarEntity;
import com.example.desafio.api.models.repositorys.ModelCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ModelCarService {

    private final ModelCarRepository modelCarRepository;

    @Autowired
    public ModelCarService(ModelCarRepository modelCarRepository) {
        this.modelCarRepository = modelCarRepository;
    }

    /**
     * Método responsável por atualizar um carro
     *
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> updateModelCar(Long id, CarDto carDto) {
        try {

            ModelCarEntity modelCarEntity = modelCarRepository.findById(id).orElseThrow(() -> new CarExceptions("Modelo não encontrado"));

            modelCarEntity.setModelName(carDto.getModelName());
            modelCarEntity.setPriceFip(carDto.getPriceFip());

            modelCarRepository.save(modelCarEntity);

            return ApiResponseService.createSuccessResponse("Modelo atualizado com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao atualizar o modelo: " + e.getMessage());
        }
    }
}
