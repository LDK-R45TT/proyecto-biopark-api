package com.bootcamp.biopark.infraestructure.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnvironmentRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre del recinto no puede superar los 50 caracteres")
        String name,
        @NotBlank(message = "Es necesaria una descripcion del recinto")
        String description
) {

}
