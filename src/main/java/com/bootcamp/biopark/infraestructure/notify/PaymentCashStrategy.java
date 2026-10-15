package com.bootcamp.biopark.infraestructure.notify;

import com.bootcamp.biopark.domain.model.Payment;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PaymentCashStrategy implements PaymentStrategy  {
    private static final Logger log = LoggerFactory.getLogger(PaymentCashStrategy.class);

    @Override
    public String type() {
        return "CASH";
    }

    @Override
    public void executeTransaction(Payment payment) {
        log.info("[Procesando Efectivo] Pago ID={} registrado en caja chica. Total: ${}",
                payment.getId(), payment.getTotalAmount());

    }
}
