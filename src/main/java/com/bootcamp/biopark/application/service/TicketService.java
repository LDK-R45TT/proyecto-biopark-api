package com.bootcamp.biopark.application.service;

import com.bootcamp.biopark.application.port.in.CreateTicketUseCase;
import com.bootcamp.biopark.application.port.in.TicketCommand;
import com.bootcamp.biopark.application.port.out.EnvironmentRepositoryPort;
import com.bootcamp.biopark.application.port.out.RateRepositoryPort;
import com.bootcamp.biopark.application.port.out.TicketRepositoryPort;
import com.bootcamp.biopark.application.port.out.VisitorRepositoryPort;
import com.bootcamp.biopark.domain.model.Environment;
import com.bootcamp.biopark.domain.model.Rate;
import com.bootcamp.biopark.domain.model.Ticket;
import com.bootcamp.biopark.domain.model.Visitor;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements CreateTicketUseCase {

    private final TicketRepositoryPort ticketRepository;
    private final VisitorRepositoryPort visitorRepository;
    private final EnvironmentRepositoryPort environmentRepository;
    private final RateRepositoryPort rateRepository;
    public TicketService(TicketRepositoryPort ticketRepository,
                         VisitorRepositoryPort visitorRepository,
                         EnvironmentRepositoryPort environmentRepository,
                         RateRepositoryPort rateRepository) {
        this.ticketRepository = ticketRepository;
        this.visitorRepository = visitorRepository;
        this.environmentRepository = environmentRepository;
        this.rateRepository = rateRepository;
    }

    @Override
    public Ticket sellTicket(TicketCommand command) {
        Visitor visitorFound = visitorRepository.findById(command.visitorId())
                .orElseThrow(()-> new ResourceNotFoundException("visitor no existe"));

        Environment environmentFound = environmentRepository.findById(command.environment())
                .orElseThrow(()-> new ResourceNotFoundException("recinto no existe"));

        Rate rateFound = rateRepository.findById(command.rate())
                .orElseThrow(()-> new ResourceNotFoundException("tarifa no existe"));

        return ticketRepository.save(new Ticket(null, visitorFound.getId(),true, rateFound, environmentFound));

    }
}
