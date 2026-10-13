package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Visitor;

import java.util.List;

public interface CreateVisitorUseCase {
    Visitor create(VisitorCommand command);
    List<Visitor> createAll(List<VisitorCommand> command);
}
