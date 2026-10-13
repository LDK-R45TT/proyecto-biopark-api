package com.bootcamp.biopark.application.port.in;

public interface DeleteVisitorUseCase {

    void deleteById(Long idVisitor);
    void deleteAll();
}
