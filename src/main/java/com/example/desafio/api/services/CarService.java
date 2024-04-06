package com.example.desafio.api.services;

import com.example.desafio.api.exceptions.CarExceptions;
import com.example.desafio.api.models.dtos.CarDto;
import com.example.desafio.api.models.entitys.CarEntity;
import com.example.desafio.api.models.repositorys.BrandRepository;
import com.example.desafio.api.models.repositorys.CarRepository;
import com.example.desafio.api.models.repositorys.ModelCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
     * @param carDto
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> registerCar(CarDto carDto) {
        try {
            // Buscando a marca pelo nome
            var brandEntity = brandCarRepository.findByNameBrand(carDto.getBrandName());

            // verifica se a marca já existe, se não existir cadastra
            if (brandEntity == null) {
                brandEntity = carDto.toBrand();
                brandCarRepository.save(brandEntity);
            }

            // buscando o modelo pelo nome
            var modelCar = modelCarRepository.findByModelName(carDto.getModelName());

            // verifica se o modelo já existe, se não existir cadastra
            if (modelCar == null) {
                modelCar = carDto.toModelCar(brandEntity);
                modelCarRepository.save(modelCar);
            }

            // Cadastrando o carro
            CarEntity carEntity = carDto.toCar(modelCar);
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
     * @param carDto
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> updateCar(Long id, CarDto carDto) {
        try {

            CarEntity carToUpdate = carRepository.findById(id).orElseThrow(CarExceptions::new);

            if (carToUpdate == null) {
                throw new CarExceptions();
            }

            // Atualizando carro
            carToUpdate.setFuel(carDto.getFuel());
            carToUpdate.setColor(carDto.getColor());
            carToUpdate.setYear(carDto.getYear());
            carToUpdate.setNumDoors(carDto.getNumDoors());

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
            CarEntity carToDelete = carRepository.findById(id).orElseThrow(CarExceptions::new);

            if (carToDelete == null) {
                throw new CarExceptions();
            }

            carToDelete.setDeleted(true);

            carRepository.save(carToDelete);

            return ApiResponseService.createSuccessResponse("Carro deletado com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao deletar o carro:" + e.getMessage());
        }
    }
}
