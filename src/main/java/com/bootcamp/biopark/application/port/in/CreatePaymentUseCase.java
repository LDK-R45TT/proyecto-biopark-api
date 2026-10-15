package com.bootcamp.biopark.application.port.in;


import com.bootcamp.biopark.domain.model.Payment;

public interface CreatePaymentUseCase {
    Payment processPayment(PaymentCommand command);
}
