package com.bootcamp.biopark.infraestructure.out.repository;

import com.bootcamp.biopark.infraestructure.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateJpaRepository extends JpaRepository<RateEntity, Long> {


}
