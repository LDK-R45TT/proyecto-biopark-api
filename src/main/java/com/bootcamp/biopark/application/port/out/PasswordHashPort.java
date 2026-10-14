package com.bootcamp.biopark.application.port.out;

//generador de hash
public interface PasswordHashPort {
    String hash(String passwordRaw);
}

