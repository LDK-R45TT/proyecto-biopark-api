package com.bootcamp.biopark.infraestructure.out.repository;

import com.bootcamp.biopark.infraestructure.entity.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorJpaRepository extends JpaRepository<VisitorEntity, Long> {




}
