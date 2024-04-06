package com.example.desafio.api.models.repositorys;

import com.example.desafio.api.models.entitys.ModelCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelCarRepository extends JpaRepository<ModelCarEntity, Long> {
    ModelCarEntity findByModelName(String modelName);
}
