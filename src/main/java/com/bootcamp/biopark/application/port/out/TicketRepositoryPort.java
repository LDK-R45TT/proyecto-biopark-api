package com.bootcamp.biopark.application.port.out;

import com.bootcamp.biopark.domain.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long id);
    List<Ticket> findAll();
}
