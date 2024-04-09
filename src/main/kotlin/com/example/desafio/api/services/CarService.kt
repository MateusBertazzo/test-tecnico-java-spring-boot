package com.example.desafio.api.services

import com.example.desafio.api.exceptions.CarExceptions
import com.example.desafio.api.models.dtos.CarAndModelAndBrandDto
import com.example.desafio.api.models.dtos.CarRequestDto
import com.example.desafio.api.models.repositorys.BrandRepository
import com.example.desafio.api.models.repositorys.CarRepository
import com.example.desafio.api.models.repositorys.ModelCarRepository
import com.example.desafio.api.services.ApiResponseService.Companion.createErrorResponse
import com.example.desafio.api.services.ApiResponseService.Companion.createSuccessResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CarService @Autowired constructor
    (
            private val carRepository: CarRepository,
            private val modelCarRepository: ModelCarRepository,
            private val brandCarRepository: BrandRepository
    )
{

    /**
     * Método responsável por registrar um carro
     *
     * @param carAndModelAndBrandDto
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun registerCar(carAndModelAndBrandDto: CarAndModelAndBrandDto): ResponseEntity<ApiResponseService> {
        return try {

            // Buscando a marca pelo nome
            var brandEntity = brandCarRepository.findByNameBrand(carAndModelAndBrandDto.nameBrand)

            // verifica se a marca já existe, se não existir cadastra
            if (brandEntity == null) {
                brandEntity = carAndModelAndBrandDto.toBrand()
                brandCarRepository.save(brandEntity)
            }

            // Buscando o modelo pelo nome
            var modelCarEntity = modelCarRepository.findByModelName(carAndModelAndBrandDto.modelName)

            // verifica se o modelo já existe, se não existir cadastra
            if (modelCarEntity == null) {
                modelCarEntity = carAndModelAndBrandDto.toModelCar(brandEntity)
                modelCarRepository.save(modelCarEntity)
            }

            // Cadastrando o carro
            val carEntity = carAndModelAndBrandDto.toCar(modelCarEntity)
            carRepository.save(carEntity)

            return createSuccessResponse("Carro registrado com sucesso", null)

        } catch (e: Exception) {
            createErrorResponse("Erro ao registrar o carro: " + e.message)
        }
    }

    /**
     * Método responsável por retornar todos os carros e o nome do modelo associado
     *
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun findAllCarsAndModelName(): ResponseEntity<ApiResponseService> {
        return try {

            val getAllCars = carRepository.findAllCarsAndModelName()

            return createSuccessResponse("Carros retornados com sucesso!", getAllCars)

        } catch (e: Exception) {
            createErrorResponse("Erro ao buscar os carros: " + e.message)
        }
    }

    /**
     * Método responsável por atualizar um carro
     *
     * @param id
     * @param carRequestDto
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun updateCar(id: Long, carRequestDto: CarRequestDto): ResponseEntity<ApiResponseService> {
        return try {

            // Buscando o carro pelo id
            var carToUpdate = carRepository.findById(id).orElseThrow { CarExceptions() } ?: throw CarExceptions()

            // Atualizando carro
            carToUpdate.fuel = carRequestDto.fuel
            carToUpdate.color = carRequestDto.color
            carToUpdate.year = carRequestDto.year
            carToUpdate.numDoors = carRequestDto.numDoors

            carRepository.save(carToUpdate)

            return createSuccessResponse("Carro atualizado com sucesso", null)

        } catch (e: Exception) {
            createErrorResponse("Erro ao atualizar o carro: " + e.message)
        }
    }

    /**
     * Método responsável por deletar um carro
     *
     * @param id
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun deleteCar(id: Long): ResponseEntity<ApiResponseService> {
        return try {

            // Buscando o carro pelo id
            val carToDelete = carRepository.findById(id).orElseThrow { CarExceptions("Carro não encontrado") }!!

            carToDelete.deleted = true

            carRepository.save(carToDelete)

            return createSuccessResponse("Carro deletado com sucesso", null)
        } catch (e: Exception) {
            createErrorResponse("Erro ao deletar o carro:" + e.message)
        }
    }
}