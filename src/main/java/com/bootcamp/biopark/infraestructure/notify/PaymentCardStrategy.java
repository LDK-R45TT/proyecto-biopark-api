package com.bootcamp.biopark.infraestructure.notify;

import com.bootcamp.biopark.domain.model.Payment;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


@Component
class PaymentCardStrategy implements PaymentStrategy {
    private static final Logger log = LoggerFactory.getLogger(PaymentCardStrategy.class);


    @Override
    public String type() {
        return "CARD";
    }

    @Override
    public void executeTransaction(Payment payment) {
        log.info("[Procesando Tarjeta] Conectando con pasarela " +
                        "de pagos externa para el Pago ID={}. Total: ${}"
        );
    }
}
