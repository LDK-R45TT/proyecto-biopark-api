package com.bootcamp.biopark.application.port.in;

public record UserCommand(
        String name,
        String email,
        String password
) {
}
