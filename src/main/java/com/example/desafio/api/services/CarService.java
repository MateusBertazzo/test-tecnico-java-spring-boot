package com.example.desafio.api.services;

import com.example.desafio.api.exceptions.CarExceptions;
import com.example.desafio.api.models.dtos.CarAndModelAndBrandDto;
import com.example.desafio.api.models.dtos.CarRequestDto;
import com.example.desafio.api.models.entitys.CarEntity;
import com.example.desafio.api.models.entitys.ModelCarEntity;
import com.example.desafio.api.models.repositorys.BrandRepository;
import com.example.desafio.api.models.repositorys.CarRepository;
import com.example.desafio.api.models.repositorys.ModelCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Calendar;

@Service
public class CarService {
    private final CarRepository carRepository;

    private final ModelCarRepository modelCarRepository;

    private final BrandRepository brandCarRepository;

    @Autowired
    public CarService(CarRepository carRepository, ModelCarRepository modelCarRepository, BrandRepository brandCarRepository) {
        this.carRepository = carRepository;
        this.modelCarRepository = modelCarRepository;
        this.brandCarRepository = brandCarRepository;
    }

    /**
     * Método responsável por registrar um carro
     *
     * @param carAndModelAndBrandDto
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> registerCar(CarAndModelAndBrandDto carAndModelAndBrandDto) {
        try {

            // Buscando a marca pelo nome
            var brandEntity = brandCarRepository.findByNameBrand(carAndModelAndBrandDto.getNameBrand());

            // verifica se a marca já existe, se não existir cadastra
            if (brandEntity == null) {
                brandEntity = carAndModelAndBrandDto.toBrand();
                brandCarRepository.save(brandEntity);
            }

            // Buscando o modelo pelo nome
            ModelCarEntity modelCarEntity = modelCarRepository.findByModelName(carAndModelAndBrandDto.getModelName());

            // verifica se o modelo já existe, se não existir cadastra
            if (modelCarEntity == null) {
                modelCarEntity = carAndModelAndBrandDto.toModelCar(brandEntity);
                modelCarRepository.save(modelCarEntity);
            }

            // Cadastrando o carro
            CarEntity carEntity = carAndModelAndBrandDto.toCar(modelCarEntity);
            carRepository.save(carEntity);

            return ApiResponseService.createSuccessResponse("Carro registrado com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao registrar o carro: " + e.getMessage());
        }
    }


    /**
     * Método responsável por retornar todos os carros e o nome do modelo associado
     *
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> findAllCarsAndModelName() {

        try {
            var getAllCars = carRepository.findAllCarsAndModelName();

            return ApiResponseService.createSuccessResponse("Carros retornados com sucesso!", getAllCars);
        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao buscar os carros: " + e.getMessage());
        }
    }

    /**
     * Método responsável por atualizar um carro
     *
     * @param id
     * @param carRequestDto
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> updateCar(Long id, CarRequestDto carRequestDto) {
        try {

            // Buscando o carro pelo id
            CarEntity carToUpdate = carRepository.findById(id).orElseThrow(CarExceptions::new);

            // Atualizando carro
            carToUpdate.setFuel(carRequestDto.getFuel());
            carToUpdate.setColor(carRequestDto.getColor());
            carToUpdate.setYear(carRequestDto.getYear());
            carToUpdate.setNumDoors(carRequestDto.getNumDoors());

            carRepository.save(carToUpdate);

            return ApiResponseService.createSuccessResponse("Carro atualizado com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao atualizar o carro: " + e.getMessage());
        }
    }

    /**
     * Método responsável por deletar um carro
     *
     * @param id
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> deleteCar(Long id) {
        try {

            // Buscando o carro pelo id
            CarEntity carToDelete = carRepository.findById(id).orElseThrow(() -> new CarExceptions("Carro não encontrado"));

            carToDelete.setDeleted(true);

            carRepository.save(carToDelete);

            return ApiResponseService.createSuccessResponse("Carro deletado com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao deletar o carro:" + e.getMessage());
        }
    }
}
