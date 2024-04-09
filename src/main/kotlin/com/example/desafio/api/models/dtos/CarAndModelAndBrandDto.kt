package com.example.desafio.api.models.dtos

import com.example.desafio.api.models.customValidation.MaxYear
import com.example.desafio.api.models.entitys.BrandEntity
import com.example.desafio.api.models.entitys.CarEntity
import com.example.desafio.api.models.entitys.ModelCarEntity
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneOffset

class CarAndModelAndBrandDto {

    @field:NotBlank(message = "O nome da marca é obrigatório")
    var nameBrand: String? = null

    @field:NotBlank(message = "O nome do modelo é obrigatório")
    var modelName: String? = null

    // validator customizado
    @MaxYear
    @field:Min(value = 1900, message = "O ano deve ser maior que 1900")
    @field:NotNull(message = "O ano é obrigatório")
    var year: Int? = null

    @field:NotBlank(message = "O combustível é obrigatório")
    var fuel: String? = null

    @field:Min(value = 2, message = "O número de portas deve ser igual ou maior que 2")
    @field:NotNull(message = "O número de portas é obrigatório")
    var numDoors: Int? = null

    @field:NotBlank(message = "A cor é obrigatória")
    var color: String? = null

    @field:NotNull(message = "O preço FIP é obrigatório")
    @field:Min(value = 0, message = "O preço FIP deve ser maior que 0")
    var priceFip: BigDecimal? = null

    // Converte um DTO para uma entidade Brand
    fun toBrand(): BrandEntity {
        var marcaEntity = BrandEntity()
        marcaEntity.nameBrand = nameBrand
        return marcaEntity
    }

    // Converte um DTO para uma entidade ModelCar
    fun toModelCar(brand: BrandEntity?): ModelCarEntity {

        var modelCarEntity = ModelCarEntity()

        modelCarEntity.modelName =  modelName
        modelCarEntity.priceFip =  priceFip
        modelCarEntity.brandId = brand

        return modelCarEntity
    }

    //Converte um DTO para uma entidade Car
    fun toCar(modelCar: ModelCarEntity?): CarEntity {
        var carEntity = CarEntity()

        // validando se Year é maior que o ano atual
        carEntity.color = color
        carEntity.year = year
        carEntity.fuel = fuel
        carEntity.numDoors = numDoors

        // Convertendo LocalDateTime para timesTamp Unix
        carEntity.timestampRegister = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        carEntity.modelId = modelCar
        return carEntity
    }
}