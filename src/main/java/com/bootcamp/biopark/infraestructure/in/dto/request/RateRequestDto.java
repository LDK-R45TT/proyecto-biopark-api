package com.bootcamp.biopark.infraestructure.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RateRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre de la tarifa no puede superar los 50 caracteres")
        String name,
        @Positive
        @NotBlank(message = "La tarifa debe tener un precio")
        Double price,
        @NotBlank(message = "Es necesaria una descripcion de la tarifa")
        String description

) {

}
