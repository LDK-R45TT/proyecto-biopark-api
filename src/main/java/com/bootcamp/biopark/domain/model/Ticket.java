package com.bootcamp.biopark.domain.model;


public class Ticket {
    private final Long id;
    private final Long visitor;
    private final Boolean active;
    private final Rate rate;
    private final Environment environment;



    public Ticket(Long id, Long visitor, Boolean active, Rate rate, Environment environment) {
        // 1. Regla de Negocio: ¡No se puede crear un ticket si el ambiente está cerrado!
        if (environment == null || !environment.getActive()) {
            throw new IllegalStateException("No se pueden emitir tickets para un ambiente inactivo o cerrado.");
        }

        // 2. Regla de Negocio: El precio se congela al valor actual de la tarifa
        if (rate == null) {
            throw new IllegalArgumentException("El ticket requiere una tarifa válida.");
        }

        this.id = id;
        this.visitor = visitor;
        this.active = active;
        this.rate = rate;
        this.environment = environment;
    }


    public Long getId() {
        return id;
    }

    public Long getVisitor() {
        return visitor;
    }

    public Boolean getActive() {
        return active;
    }

    public Rate getRate() {
        return rate;
    }

    public Environment getEnvironment() {
        return environment;
    }
}
