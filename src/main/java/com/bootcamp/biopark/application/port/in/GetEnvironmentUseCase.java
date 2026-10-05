package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Environment;

import java.util.List;

public interface GetEnvironmentUseCase {

    Environment findById(Long id);
    List<Environment> findAll();
}
