package com.bootcamp.biopark.infraestructure.security;


import com.bootcamp.biopark.application.port.out.TokenGeneratorPort;
import com.bootcamp.biopark.domain.model.Role;
import com.bootcamp.biopark.infraestructure.security.service.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenIssuer implements TokenGeneratorPort {

    private final TokenService tokenService;

    public JwtTokenIssuer(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    //genera el token al obtener credenciales
    @Override
    public String generate(String email, Role role) {
        UserDetails securityUser = new SecurityUser(null, email, "", role);
        return tokenService.generateToken(securityUser);
    }
}
