package com.bootcamp.biopark.application.port.out;


import com.bootcamp.biopark.domain.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    List<Payment> findAll();
    List<Payment> filterByVisitor(Long visitorId);

}
