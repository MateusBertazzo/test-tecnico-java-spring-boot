package com.example.desafio.api.services;

import com.example.desafio.api.exceptions.CarExceptions;
import com.example.desafio.api.models.dtos.ModelDto;
import com.example.desafio.api.models.entitys.ModelCarEntity;
import com.example.desafio.api.models.repositorys.ModelCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * @param  id
     * @param modelDto
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> updateModelCar(Long id, ModelDto modelDto) {
        try {

            ModelCarEntity modelCarEntity = modelCarRepository.findById(id).orElseThrow(() -> new CarExceptions("Modelo não encontrado"));

            modelCarEntity.setModelName(modelDto.getModelName());
            modelCarEntity.setPriceFip(modelDto.getPriceFip());

            modelCarRepository.save(modelCarEntity);

            return ApiResponseService.createSuccessResponse("Modelo atualizado com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao atualizar o modelo: " + e.getMessage());
        }
    }

    /**
     * Método responsável por buscar todos os modelos
     *
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> getAllModelCars() {
        try {
            List<ModelCarEntity> getAllModelCars = modelCarRepository.findAll();

            return ApiResponseService.createSuccessResponse("Modelos retornados com sucesso!", getAllModelCars);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao atualizar o modelo: " + e.getMessage());
        }
    }

    /**
     * Método responsável por Deletar um modelo
     *
     * @param id
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> deleteModelCar(Long id) {
        try {
            ModelCarEntity getModel = modelCarRepository.findById(id).orElseThrow(() -> new CarExceptions("Modelo não encontrado"));

            getModel.setDeleted(true);
            getModel.getCars().forEach(car -> car.setDeleted(true));

            modelCarRepository.save(getModel);

            return ApiResponseService.createSuccessResponse("Modelo Deletado com sucesso!", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao deletar modelo: " + e.getMessage());
        }
    }

}
