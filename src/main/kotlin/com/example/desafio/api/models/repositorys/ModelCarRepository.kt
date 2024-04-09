package com.example.desafio.api.models.repositorys

import com.example.desafio.api.models.entitys.ModelCarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModelCarRepository : JpaRepository<ModelCarEntity?, Long?> {
    fun findByModelName(modelName: String?): ModelCarEntity?
}