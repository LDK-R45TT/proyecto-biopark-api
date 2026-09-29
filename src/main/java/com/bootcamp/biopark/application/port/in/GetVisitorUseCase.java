package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Visitor;

import java.util.List;

public interface GetVisitorUseCase {

    Visitor findById(Long id);
    List<Visitor> findAll();

}
