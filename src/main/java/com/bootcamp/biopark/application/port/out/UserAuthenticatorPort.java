package com.bootcamp.biopark.application.port.out;


import com.bootcamp.biopark.domain.model.AuthenticatedUser;

/*
* interfaz que valida autenticacion
* implementada en UserAuthenticationAdapter
* */
public interface UserAuthenticatorPort {

    AuthenticatedUser authenticate(String email, String password);
}
