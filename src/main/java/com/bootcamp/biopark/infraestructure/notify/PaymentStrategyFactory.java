package com.bootcamp.biopark.infraestructure.notify;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentStrategyFactory {
    private final Map<String, PaymentStrategy> strategies;

    public PaymentStrategyFactory(List<PaymentStrategy> strategies) {
        this.strategies = strategies.stream()
                .collect(Collectors.toMap(PaymentStrategy::type, Function.identity()));
    }

    public PaymentStrategy forType(String type) {
        PaymentStrategy strategy = strategies.get(type.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Método de pago desconocido '" + type + "'. Disponibles: " + strategies.keySet());
        }
        return strategy;
    }
}
