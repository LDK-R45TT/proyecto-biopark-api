package com.bootcamp.biopark.infraestructure.in.exception;

public class InvalidAuthCredentialsException extends RuntimeException {
    public InvalidAuthCredentialsException(String message) {
        super(message);
    }
}