package com.bootcamp.biopark.infraestructure.notify;

import com.bootcamp.biopark.domain.model.Payment;

public interface PaymentStrategy {

    /** valor de esta estrategia */
    String type();

    void executeTransaction(Payment payment);
}
