package com.bootcamp.biopark.application.service;

import com.bootcamp.biopark.application.port.in.LoginUseCase;
import com.bootcamp.biopark.application.port.in.RegisterUseCase;
import com.bootcamp.biopark.application.port.in.UserCommand;
import com.bootcamp.biopark.application.port.out.PasswordHashPort;
import com.bootcamp.biopark.application.port.out.TokenGeneratorPort;
import com.bootcamp.biopark.application.port.out.UserAuthenticatorPort;
import com.bootcamp.biopark.application.port.out.UserSessionRepositoryPort;
import com.bootcamp.biopark.domain.model.AuthenticatedUser;
import com.bootcamp.biopark.domain.model.User;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements RegisterUseCase, LoginUseCase {

    private final UserSessionRepositoryPort users;
    private final PasswordHashPort passwordHashPort;//interfaz hasher
    private final UserAuthenticatorPort userAuthenticatorPort;
    private final TokenGeneratorPort tokenGeneratorPort;

    public AuthenticationService(UserSessionRepositoryPort users, PasswordHashPort passwordHashPort, UserAuthenticatorPort userAuthenticatorPort, TokenGeneratorPort tokenGeneratorPort) {
        this.users = users;
        this.passwordHashPort = passwordHashPort;
        this.userAuthenticatorPort = userAuthenticatorPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
    }


    @Override
    public String register(UserCommand command) {
        if (users.existsByEmail(command.email())) {
            throw new ResourceAlreadyExistsException(command.email());
        }
        User saved = users.save(User.register(command.name(), command.email(), passwordHashPort.hash(command.password())));
        return tokenGeneratorPort.generate(saved.getEmail(), saved.getRole());
    }

    @Override
    public String login(String email, String pass) {
        // Si las credenciales están mal, el adaptador lanza la exception
        AuthenticatedUser user = userAuthenticatorPort.authenticate(email, pass);
        // si pasa, devuelve el token
        return tokenGeneratorPort.generate(email, user.role());
    }
}
