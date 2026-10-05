package com.bootcamp.biopark.application.port.out;

import com.bootcamp.biopark.domain.model.Environment;

import java.util.List;
import java.util.Optional;

public interface EnvironmentRepositoryPort {
    Environment save(Environment env);
    Optional<Environment> findById(Long id);
    List<Environment> findAll();
}
