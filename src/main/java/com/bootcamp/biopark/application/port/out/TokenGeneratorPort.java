package com.bootcamp.biopark.application.port.out;

import com.bootcamp.biopark.domain.model.Role;

//interfaz para generar tokens desde JwtTokenIssuer
public interface TokenGeneratorPort {
    String generate(String email, Role role);
}
