package com.example.desafio.api.services

import com.example.desafio.api.exceptions.CarExceptions
import com.example.desafio.api.models.dtos.ModelDto
import com.example.desafio.api.models.repositorys.ModelCarRepository
import com.example.desafio.api.services.ApiResponseService.Companion.createErrorResponse
import com.example.desafio.api.services.ApiResponseService.Companion.createSuccessResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ModelCarService @Autowired constructor(private val modelCarRepository: ModelCarRepository) {
    /**
     * Método responsável por atualizar um carro
     *
     * @param  id
     * @param modelDto
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun updateModelCar(id: Long, modelDto: ModelDto): ResponseEntity<ApiResponseService> {
        return try {

            // Busca o modelo pelo id
            val modelCarEntity = modelCarRepository.findById(id).orElseThrow { CarExceptions("Modelo não encontrado") }!!

            modelCarEntity.modelName = modelDto.modelName
            modelCarEntity.priceFip = modelDto.priceFip

            modelCarRepository.save(modelCarEntity)

            createSuccessResponse("Modelo atualizado com sucesso", null)

        } catch (e: Exception) {
            createErrorResponse("Erro ao atualizar o modelo: " + e.message)
        }
    }

    /**
     * Método responsável por buscar todos os modelos
     *
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun allModelCars(): ResponseEntity<ApiResponseService> {
        return try {

            val getAllModelCars = modelCarRepository.findAll()

            createSuccessResponse("Modelos retornados com sucesso!", getAllModelCars)

        } catch (e: Exception) {
            createErrorResponse("Erro ao atualizar o modelo: " + e.message)
        }
    }


    /**
     * Método responsável por Deletar um modelo
     *
     * @param id
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun deleteModelCar(id: Long): ResponseEntity<ApiResponseService> {
        return try {

            val getModel = modelCarRepository.findById(id).orElseThrow { CarExceptions("Modelo não encontrado") }!!

            // Deleta o modelo
            getModel.deleted = true

            // Deleta todos os carros associados ao modelo
            getModel.cars?.forEach { car -> car.deleted = true }

            modelCarRepository.save(getModel)

            createSuccessResponse("Modelo Deletado com sucesso!", null)
        } catch (e: Exception) {
            createErrorResponse("Erro ao deletar modelo: " + e.message)
        }
    }
}