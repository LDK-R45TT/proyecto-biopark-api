package com.bootcamp.biopark.application.port.out;

import com.bootcamp.biopark.domain.model.Rate;

import java.util.List;
import java.util.Optional;

public interface RateRepositoryPort {
    Rate save(Rate rate);
    Optional<Rate> findById(Long id);
    List<Rate> findAll();

}
