package com.example.desafio.api.services

import com.example.desafio.api.exceptions.CarExceptions
import com.example.desafio.api.models.dtos.BrandDto
import com.example.desafio.api.models.dtos.BrandRequestDto
import com.example.desafio.api.models.repositorys.BrandRepository
import com.example.desafio.api.services.ApiResponseService.Companion.createErrorResponse
import com.example.desafio.api.services.ApiResponseService.Companion.createSuccessResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BrandService @Autowired constructor(private val brandRepository: BrandRepository) {

    /**
     * Método responsável por ATUALIZAR marca
     *
     * @param id
     * @param brandRequestDto
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun updateBrand(id: Long, brandRequestDto: BrandRequestDto): ResponseEntity<ApiResponseService> {

        return try {

            val brandEntity = brandRepository.findById(id).orElseThrow { CarExceptions("Marca não encontrada") }!!

            brandEntity.nameBrand = brandRequestDto.nameBrand

            brandRepository.save(brandEntity)

            createSuccessResponse("Marca atualizada com sucesso", null)
        } catch (e: Exception) {
            createErrorResponse("Erro ao atualizar a marca: " + e.message)
        }
    }

    /**
     * Método responsável por BUSCAR todas as marcas
     *
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun findAll(): ResponseEntity<ApiResponseService> {
        return try {
            val allBrands = brandRepository.findAll()

            // Crio uma lista de DTO que vou preencher com os valores do banco
            val brandDtos: MutableList<BrandDto> = ArrayList()
            for (brand in allBrands) {

                // Crio uma instância de DTO seto os valores, logo após, adiciono eles na lista
                val brandDto = BrandDto()

                brandDto.id = brand?.id
                brandDto.deleted = brand?.deleted
                brandDto.nameBrand = brand?.nameBrand

                brandDtos.add(brandDto)
            }

            createSuccessResponse("Marcas encontradas com sucesso", brandDtos)
        } catch (e: Exception) {
            createErrorResponse("Erro ao buscar as marcas: " + e.message)
        }
    }

    /**
     * Método responsável por DELETAR uma marca
     *
     * @param id
     * @return ResponseEntity<ApiResponseService>
    </ApiResponseService> */
    fun deleteBrand(id: Long): ResponseEntity<ApiResponseService> {
        return try {
            val brandEntity = brandRepository.findById(id).orElseThrow { CarExceptions("Marca não encontrada") }!!

            // Deleta a marca
            brandEntity.deleted = true

            // Deleta todos os modelos associados a marca
            brandEntity.modelCars?.forEach { modelCar ->
                modelCar.deleted = true
            }

            // Deleta todos os carros associados aos modelos deletados
            brandEntity.modelCars?.forEach { modelCarEntity ->
                modelCarEntity.cars?.forEach { car ->
                    car.deleted = true
                }
            }

            brandRepository.save(brandEntity)

            createSuccessResponse("Marca deletada com sucesso", null)
        } catch (e: Exception) {
            createErrorResponse("Erro ao deletar a marca: " + e.message)
        }
    }
}