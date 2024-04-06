package com.example.desafio.api.models.repositorys;

import com.example.desafio.api.models.entitys.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    public BrandEntity findByNameBrand(String nameBrand);
}
