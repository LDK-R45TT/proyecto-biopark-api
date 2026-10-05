package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Rate;

import java.util.List;

public interface GetRateUseCase {
    Rate findById(Long id);
    List<Rate> findAll();
}
