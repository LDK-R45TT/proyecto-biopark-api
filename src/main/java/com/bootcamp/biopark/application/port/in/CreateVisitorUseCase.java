package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Visitor;

public interface CreateVisitorUseCase {
    Visitor create(VisitorCommand command);
}
