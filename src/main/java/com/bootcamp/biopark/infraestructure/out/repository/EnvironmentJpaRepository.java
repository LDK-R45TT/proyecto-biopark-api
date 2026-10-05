package com.bootcamp.biopark.infraestructure.out.repository;

import com.bootcamp.biopark.infraestructure.entity.EnvironmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentJpaRepository extends JpaRepository<EnvironmentEntity, Long> {
}