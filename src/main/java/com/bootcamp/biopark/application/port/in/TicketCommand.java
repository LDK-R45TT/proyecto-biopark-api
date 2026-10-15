package com.bootcamp.biopark.application.port.in;


public record TicketCommand(
        Long visitorId,
        Long rate,
        Long environment
) {
}