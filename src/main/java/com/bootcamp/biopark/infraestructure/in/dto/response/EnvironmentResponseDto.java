package com.bootcamp.biopark.infraestructure.in.dto.response;

public record EnvironmentResponseDto(
        Long id,
        String name,
        String description
        /*Set<TicketResponseDto> tickets*/ //lista dto anidados
) {
}
