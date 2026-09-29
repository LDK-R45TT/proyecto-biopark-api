package com.bootcamp.biopark.infraestructure.in.dto.response;

public record VisitorResponseDto(
        Long idVisitor,
        String name,
        String lastName,
        String dni,
        Integer age
) {
}
