package com.bootcamp.biopark.infraestructure.security;


import com.bootcamp.biopark.application.port.out.UserAuthenticatorPort;
import com.bootcamp.biopark.domain.model.AuthenticatedUser;
import com.bootcamp.biopark.infraestructure.in.exception.InvalidAuthCredentialsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationAdapter implements UserAuthenticatorPort {

    private final AuthenticationManager authManager;

    public UserAuthenticationAdapter(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    //solo autentica al usuario y valida credenciales
    @Override
    public AuthenticatedUser authenticate(String email, String password) {
        try {//si sale bien, devuelve true
            var auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            return ((SecurityUser) auth.getPrincipal()).toAuthenticatedUser();
        } catch (AuthenticationException e) {
            //lanza excepcion personalizada del dominio
            throw new InvalidAuthCredentialsException("El usuario o contraseña son invalidas");
        }
    }
}

