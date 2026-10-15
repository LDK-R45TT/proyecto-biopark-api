package com.bootcamp.biopark.application.port.out;

import com.bootcamp.biopark.domain.model.Payment;

public interface PaymentGatewayPort {
    void executeTransaction(Payment payment);
}
