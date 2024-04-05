package com.example.desafio.api.services;

import com.example.desafio.api.exceptions.GetAllCarsException;
import com.example.desafio.api.exceptions.CarExceptions;
import com.example.desafio.api.models.dtos.CarDto;
import com.example.desafio.api.models.entitys.BrandEntity;
import com.example.desafio.api.models.entitys.CarEntity;
import com.example.desafio.api.models.entitys.ModelCarEntity;
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
            // Cadastrando a marca
            BrandEntity brandEntity = carDto.toBrand();
            brandCarRepository.save(brandEntity);

            // Cadastrando o modelo
            ModelCarEntity modelCarEntity = carDto.toModelCar(brandEntity);
            modelCarRepository.save(modelCarEntity);

            // Cadastrando o carro
            CarEntity carEntity = carDto.toCar(modelCarEntity);
            carRepository.save(carEntity);

            return ApiResponseService.createSuccessResponse(true, "Carro registrado com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao registrar o carro:" + e.getMessage());
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

            return ApiResponseService.createSuccessResponse(true, "Sucesso", getAllCars);
        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao buscar os carros:" + e.getMessage());
        }
    }

    /**
     * Método responsável por atualizar um carro
     *
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> updateCar(Long id, CarDto carDto) {
        try {

            CarEntity carToUpdate = carRepository.findById(id).orElseThrow(() -> new CarExceptions("Carro não encontrado"));

            if (carToUpdate == null) {
                return ApiResponseService.createErrorResponse("Carro não encontrado");
            }

            // Atualizando carro
            carToUpdate.setFuel(carDto.getFuel());
            carToUpdate.setColor(carDto.getColor());
            carToUpdate.setYear(carDto.getYear());
            carToUpdate.setNumDoors(carDto.getNumDoors());

            carRepository.save(carToUpdate);

            return ApiResponseService.createSuccessResponse(true, "Carro atualizado com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao atualizar o carro:" + e.getMessage());
        }
    }

    /**
     * Método responsável por deletar um carro
     *
     * @return ResponseEntity<ApiResponseService>
     */
    public ResponseEntity<ApiResponseService> deleteCar(Long id) {
        try {
            CarEntity carToDelete = carRepository.findById(id).orElseThrow(() -> new CarExceptions("Carro não encontrado"));

            if (carToDelete == null) {
                return ApiResponseService.createErrorResponse("Carro não encontrado");
            }

            carToDelete.setDeleted(true);

            carRepository.save(carToDelete);

            return ApiResponseService.createSuccessResponse(true, "Carro deletado com sucesso", null);

        } catch (Exception e) {
            return ApiResponseService.createErrorResponse("Erro ao deletar o carro:" + e.getMessage());
        }
    }
}
