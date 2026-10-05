package com.bootcamp.biopark.application.service;

import com.bootcamp.biopark.application.port.in.CreateRateUseCase;
import com.bootcamp.biopark.application.port.in.GetRateUseCase;
import com.bootcamp.biopark.application.port.in.RateCommand;
import com.bootcamp.biopark.application.port.out.RateRepositoryPort;
import com.bootcamp.biopark.domain.model.Rate;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceNotFoundException;

import java.util.List;

public class RateService implements CreateRateUseCase, GetRateUseCase {

    private final RateRepositoryPort repository;

    public RateService(RateRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Rate create(RateCommand command) {
        Rate rate = new Rate(null, command.name(), command.price(), command.dec());
        return repository.save(rate);
    }

    @Override
    public Rate findById(Long id) {
        Rate rateFound = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("la tarifa con id="+id+" no existe"));
        return rateFound;
    }

    @Override
    public List<Rate> findAll() {
        return repository.findAll();
    }
}
