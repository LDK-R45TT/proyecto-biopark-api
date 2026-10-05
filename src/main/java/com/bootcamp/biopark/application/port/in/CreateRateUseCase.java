package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Rate;

public interface CreateRateUseCase {
    Rate create(RateCommand command);
}
