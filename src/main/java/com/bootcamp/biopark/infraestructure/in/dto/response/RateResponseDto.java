package com.bootcamp.biopark.infraestructure.in.dto.response;

public record RateResponseDto(
        Long idRate,
        String name,
        Double price,
        String description
) {
}
