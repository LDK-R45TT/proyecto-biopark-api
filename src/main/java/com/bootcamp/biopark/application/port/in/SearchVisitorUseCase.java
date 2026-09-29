package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Visitor;

import java.util.List;

public interface SearchVisitorUseCase {
    List<Visitor> findBySurname(String surname);
}
