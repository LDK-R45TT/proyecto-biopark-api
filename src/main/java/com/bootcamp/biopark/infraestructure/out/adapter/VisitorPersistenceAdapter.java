package com.bootcamp.biopark.infraestructure.out.adapter;

import com.bootcamp.biopark.application.port.out.VisitorRepositoryPort;
import com.bootcamp.biopark.domain.model.Visitor;
import com.bootcamp.biopark.infraestructure.entity.VisitorEntity;
import com.bootcamp.biopark.infraestructure.out.mapper.VisitorPersistenceMapper;
import com.bootcamp.biopark.infraestructure.out.repository.VisitorJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VisitorPersistenceAdapter implements VisitorRepositoryPort {

    private final VisitorJpaRepository repository;
    private final VisitorPersistenceMapper mapper;

    public VisitorPersistenceAdapter(VisitorJpaRepository repository, VisitorPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Visitor save(Visitor visitor) {
        VisitorEntity visitorSaved = repository.save(mapper.toEntity(visitor));
        return mapper.toModel(visitorSaved);
    }

    @Override
    public Optional<Visitor> findById(Long id) {
        // Si no existe, esto revienta con NoSuchElementException -> HTTP 500 generico

        Optional<VisitorEntity> visitorEntity = repository.findById(id);

        return visitorEntity.map( mapper::toModel);
    }

    @Override
    public List<Visitor> findAll() {
        return repository.findAll().stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Visitor> findBySurnameIgnoreCase(String surname) {
        return repository.findBySurnameIgnoreCase(surname).stream()
                .map(mapper::toModel)
                .toList();
    }

    @Override
    public Optional<Visitor> findByDni(String dni) {
        Optional<VisitorEntity> visitorFound = repository.findByDni(dni);
        return visitorFound.map(mapper::toModel);

    }
}
