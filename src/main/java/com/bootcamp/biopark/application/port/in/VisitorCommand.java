package com.bootcamp.biopark.application.port.in;

public record VisitorCommand(
        String name,
        String surname,
        String dni,
        Integer age
) {
}
