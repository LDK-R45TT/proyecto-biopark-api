package com.bootcamp.biopark.application.port.in;

public interface CreateVisitorUseCase {
    Visitor create(VisitorCommand command);
}
