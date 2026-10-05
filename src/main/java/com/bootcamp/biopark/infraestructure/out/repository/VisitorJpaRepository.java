package com.bootcamp.biopark.infraestructure.out.repository;

import com.bootcamp.biopark.infraestructure.entity.VisitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitorJpaRepository extends JpaRepository<VisitorEntity, Long> {

    List<VisitorEntity> findBySurnameIgnoreCase(String surname);
    Optional<VisitorEntity> findByDni(String dni);

}
