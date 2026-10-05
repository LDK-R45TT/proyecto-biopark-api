package com.bootcamp.biopark.application.port.in;

public record EnvironmentCommand(
        String name,
        String description
) {
}
