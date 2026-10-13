package com.bootcamp.biopark.infraestructure.in.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank
                           String email,
                           @NotBlank String password) {
}
