package com.bootcamp.biopark.application.service;

import com.bootcamp.biopark.application.port.in.*;
import com.bootcamp.biopark.application.port.out.VisitorRepositoryPort;
import com.bootcamp.biopark.domain.model.Visitor;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceDuplicatedException;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceNotFoundException;
import com.bootcamp.biopark.infraestructure.out.mapper.VisitorPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService implements CreateVisitorUseCase, GetVisitorUseCase, SearchVisitorUseCase, DeleteVisitorUseCase {

    private final VisitorRepositoryPort repository;

    public VisitorService(VisitorRepositoryPort repository) {
        this.repository = repository;
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
    public List<Visitor> createAll(List<VisitorCommand> command) {
        return command.stream().map(this::create).toList();
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
    public List<Visitor> filterBySurname(String surname) {
        return repository.filterBySurnameIgnoreCase(surname);
    }

    @Override
    public void deleteById(Long idVisitor) {
        repository.deleteById(idVisitor);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}

