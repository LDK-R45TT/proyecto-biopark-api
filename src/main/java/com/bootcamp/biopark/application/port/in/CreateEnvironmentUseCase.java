package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Environment;

public interface CreateEnvironmentUseCase {
    Environment create(EnvironmentCommand command);

}
