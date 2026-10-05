package com.bootcamp.biopark.application.service;

import com.bootcamp.biopark.application.port.in.CreateVisitorUseCase;
import com.bootcamp.biopark.application.port.in.GetVisitorUseCase;
import com.bootcamp.biopark.application.port.in.SearchVisitorUseCase;
import com.bootcamp.biopark.application.port.in.VisitorCommand;
import com.bootcamp.biopark.application.port.out.VisitorRepositoryPort;
import com.bootcamp.biopark.domain.model.Visitor;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceDuplicatedException;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceNotFoundException;
import com.bootcamp.biopark.infraestructure.out.mapper.VisitorPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService implements CreateVisitorUseCase, GetVisitorUseCase, SearchVisitorUseCase {

    private final VisitorRepositoryPort repository;
    private final VisitorPersistenceMapper mapper;
    public VisitorService(VisitorRepositoryPort repository, VisitorPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Visitor create(VisitorCommand command) {
        repository.findByDni(command.dni()).ifPresent(visitor -> {
            throw new ResourceDuplicatedException("¡El visitante con DNI " + command.dni() + " ya se encuentra registrado!");
        });
        Visitor visitor = new Visitor(null, command.name(), command.surname(), command.dni(), command.age());

        return repository.save(visitor);
    }

    @Override
    public Visitor findById(Long id) {
        Visitor visitorFound = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("el visitante con id="+id+" no existe"));
        return visitorFound;
    }

    @Override
    public List<Visitor> findAll() {
        return repository.findAll();
    }
    public Visitor findByDni(String dni){
        Visitor visitorFound = repository.findByDni(dni)
                .orElseThrow(()->
                        new ResourceNotFoundException("el visitante con dni="+dni+" no existe"));
        return visitorFound;
    }
    @Override
    public List<Visitor> findBySurname(String surname) {
        return repository.findBySurnameIgnoreCase(surname);
    }
}

