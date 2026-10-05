package com.bootcamp.biopark.infraestructure.out.adapter;

import com.bootcamp.biopark.application.port.out.EnvironmentRepositoryPort;
import com.bootcamp.biopark.domain.model.Environment;
import com.bootcamp.biopark.infraestructure.entity.EnvironmentEntity;
import com.bootcamp.biopark.infraestructure.out.mapper.EnvironmentPersistenceMapper;
import com.bootcamp.biopark.infraestructure.out.repository.EnvironmentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EnvironmentPersistenceAdapter implements EnvironmentRepositoryPort {

    private final EnvironmentJpaRepository repository;
    private final EnvironmentPersistenceMapper mapper;

    public EnvironmentPersistenceAdapter(EnvironmentJpaRepository repository, EnvironmentPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Environment save(Environment env) {
        EnvironmentEntity entity= repository.save(mapper.toEntity(env));
        return mapper.toModel(entity);
    }

    @Override
    public Optional<Environment> findById(Long id) {
        // Si no existe, esto revienta con NoSuchElementException -> HTTP 500 generico

        Optional<EnvironmentEntity> entity = repository.findById(id);

        return entity.map( mapper::toModel);
    }

    @Override
    public List<Environment> findAll() {
        return repository.findAll().stream().map(mapper::toModel).toList();
    }
}
