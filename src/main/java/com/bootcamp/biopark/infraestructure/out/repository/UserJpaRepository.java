package com.bootcamp.biopark.infraestructure.out.repository;

import com.bootcamp.biopark.infraestructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByNameIgnoreCase(String name);
}
