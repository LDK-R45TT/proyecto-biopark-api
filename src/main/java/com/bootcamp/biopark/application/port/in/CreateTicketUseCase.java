package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Ticket;

public interface CreateTicketUseCase {
    Ticket sellTicket(TicketCommand command);
}
