package com.bootcamp.biopark.application.service;

import com.bootcamp.biopark.application.port.out.UserSessionRepositoryPort;
import com.bootcamp.biopark.domain.model.User;
import com.bootcamp.biopark.infraestructure.in.exception.InvalidAuthCredentialsException;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceAlreadyExistsException;
import com.bootcamp.biopark.infraestructure.security.SecurityUser;
import com.bootcamp.biopark.infraestructure.security.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserSessionRepositoryPort users;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationService(UserSessionRepositoryPort users, PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager, TokenService tokenService) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String register(String name, String email, String rawPassword) {
        if (users.existsByEmail(email)) {
            throw new ResourceAlreadyExistsException("El correo electrónico '" + email + "' ya se encuentra registrado.");
        }
        User saved = users.save(User.register(name, email, passwordEncoder.encode(rawPassword)));
        return tokenService.generateToken(SecurityUser.toSecurityUserFromDomain(saved));
    }

    public String login(String email, String rawPassword) {
        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, rawPassword));
            return tokenService.generateToken((UserDetails) auth.getPrincipal());
        }catch (AuthenticationException e) {
            throw new InvalidAuthCredentialsException("El usuario o la contraseña no coinciden");
        }
    }

}
