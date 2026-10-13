package com.bootcamp.biopark.infraestructure.in.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "El nombre no puede estar vacío.")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres.")
        String name,
        @NotBlank(message = "El correo electrónico no puede estar vacío.")
        @Email(message = "Debe ingresar una dirección de correo electrónico válida.")
        @Size(max = 150, message = "El correo no puede superar los 150 caracteres.")
        String email,
        @NotBlank(message = "La contraseña no puede estar vacía.")
        @Size(min = 8, max = 30, message = "La contraseña debe tener entre 8 y 30 caracteres.")
        String password) {
}
