package com.example.desafio.api.models.repositorys

import com.example.desafio.api.models.entitys.BrandEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository : JpaRepository<BrandEntity?, Long?> {
    fun findByNameBrand(nameBrand: String?): BrandEntity?
}