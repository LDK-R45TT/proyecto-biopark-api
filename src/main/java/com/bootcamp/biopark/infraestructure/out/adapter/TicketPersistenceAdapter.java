package com.bootcamp.biopark.infraestructure.out.adapter;


import com.bootcamp.biopark.application.port.out.TicketRepositoryPort;
import com.bootcamp.biopark.domain.model.Ticket;
import com.bootcamp.biopark.infraestructure.entity.TicketEntity;
import com.bootcamp.biopark.infraestructure.out.mapper.TicketPersistenceMapper;
import com.bootcamp.biopark.infraestructure.out.repository.TicketJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TicketPersistenceAdapter implements TicketRepositoryPort {
    private final TicketJpaRepository repository;
    private final TicketPersistenceMapper mapper;

    public TicketPersistenceAdapter(TicketJpaRepository repository, TicketPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity ticketSaved= repository.save(mapper.toEntity(ticket));
        return mapper.toDomain(ticketSaved);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Ticket> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }
}
