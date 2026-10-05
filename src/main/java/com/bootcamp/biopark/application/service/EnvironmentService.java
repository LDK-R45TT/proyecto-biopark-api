package com.bootcamp.biopark.application.service;

import com.bootcamp.biopark.application.port.in.CreateEnvironmentUseCase;
import com.bootcamp.biopark.application.port.in.EnvironmentCommand;
import com.bootcamp.biopark.application.port.in.GetEnvironmentUseCase;
import com.bootcamp.biopark.application.port.out.EnvironmentRepositoryPort;
import com.bootcamp.biopark.domain.model.Environment;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentService implements CreateEnvironmentUseCase, GetEnvironmentUseCase {

    private final EnvironmentRepositoryPort repository;

    public EnvironmentService(EnvironmentRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Environment create(EnvironmentCommand command) {
        Environment env = new Environment(null, command.name(), command.description());
        return repository.save(env);

    }

    @Override
    public Environment findById(Long id) {
        Environment envFound = repository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("el recinto con id="+id+" no existe"));
        return envFound;
    }

    @Override
    public List<Environment> findAll() {
        return repository.findAll();
    }


}
