package com.bootcamp.biopark.application.port.in;

import com.bootcamp.biopark.domain.model.Payment;

import java.util.List;

public interface GetPaymentUseCase {

    List<Payment> findAll();

}
