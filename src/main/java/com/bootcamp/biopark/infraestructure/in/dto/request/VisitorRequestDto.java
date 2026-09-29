package com.bootcamp.biopark.infraestructure.in.dto.request;

import jakarta.validation.constraints.*;

//dto resquest y validaciones
public  record VisitorRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
        String name,
        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 50, message = "El apellido no puede superar los 50 caracteres")
        String lastName,
        @NotBlank(message = "El DNI es obligatorio")
        @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
        @Pattern(regexp = "^[0-9]+$", message = "El DNI debe contener solo números")
        String dni,
        @NotNull(message = "La edad es obligatoria")
        @Positive(message = "La edad debe ser positiva")
        Integer age

) {
}