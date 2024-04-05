package com.example.desafio.api.services;

import com.example.desafio.api.exceptions.GetAllCarsException;
import com.example.desafio.api.exceptions.PostRegisterCarException;
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

import java.util.List;

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
     * @param   carDto
     * @return  void
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
     * @return  List<Object[]>
     */
    public ResponseEntity<ApiResponseService> findAllCarsAndModelName() {

        try {
            var getAllCars = carRepository.findAllCarsAndModelName();

            return ApiResponseService.createSuccessResponse(true, "Sucesso", getAllCars);
        } catch (Exception e) {
            throw new GetAllCarsException();
        }
    }
}
