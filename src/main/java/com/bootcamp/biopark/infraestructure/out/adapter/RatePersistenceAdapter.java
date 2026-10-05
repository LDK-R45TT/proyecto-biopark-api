package com.bootcamp.biopark.infraestructure.out.adapter;

import com.bootcamp.biopark.application.port.out.RateRepositoryPort;
import com.bootcamp.biopark.domain.model.Rate;
import com.bootcamp.biopark.infraestructure.entity.RateEntity;
import com.bootcamp.biopark.infraestructure.out.mapper.RatePersistenceMapper;
import com.bootcamp.biopark.infraestructure.out.repository.RateJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RatePersistenceAdapter implements RateRepositoryPort {

    private final RateJpaRepository repository;
    private final RatePersistenceMapper mapper;

    public RatePersistenceAdapter(RateJpaRepository repository, RatePersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Rate save(Rate rate) {
        RateEntity rateSaved = repository.save(mapper.toEntity(rate));
        return mapper.toModel(rateSaved);

    }

    @Override
    public Optional<Rate> findById(Long id) {
        Optional<RateEntity> rateFound = repository.findById(id);
        return rateFound.map(mapper::toModel);
    }

    @Override
    public List<Rate> findAll() {
        return repository.findAll().stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }
}

