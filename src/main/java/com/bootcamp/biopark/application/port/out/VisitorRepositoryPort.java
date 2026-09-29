package com.bootcamp.biopark.application.port.out;

import com.bootcamp.biopark.domain.model.Visitor;

import java.util.List;
import java.util.Optional;

/*interfaz puerto de salida
* define operaciones en base de datos*/
public interface VisitorRepositoryPort {
    //operaciones iniciales
    Visitor save(Visitor product);

    Optional<Visitor> findById(Long id);

    List<Visitor> findAll();

    List<Visitor> findBySurnameIgnoreCase(String surname);
}
