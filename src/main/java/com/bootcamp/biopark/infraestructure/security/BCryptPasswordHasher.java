package com.bootcamp.biopark.infraestructure.security;

import com.bootcamp.biopark.application.port.out.PasswordHashPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class BCryptPasswordHasher implements PasswordHashPort {

    final PasswordEncoder passwordEncoder;

    public BCryptPasswordHasher(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    //metodo que crea el hash
    @Override
    public String hash(String passwordRaw) {
        return passwordEncoder.encode(passwordRaw);
    }
}
