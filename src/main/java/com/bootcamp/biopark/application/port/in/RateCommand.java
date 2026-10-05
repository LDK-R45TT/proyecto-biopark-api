package com.bootcamp.biopark.application.port.in;

public record RateCommand(
        String name,
        Double price,
        String dec
) {
}
