package com.bootcamp.biopark.infraestructure.notify;

import com.bootcamp.biopark.application.port.out.PaymentGatewayPort;
import com.bootcamp.biopark.domain.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class PaymentGatewayAdapter implements PaymentGatewayPort {

    private final PaymentStrategy strategy;

    PaymentGatewayAdapter(PaymentStrategyFactory factory,
                          @Value("${app.payments.default-type:CASH}") String type) {
        this.strategy = factory.forType(type);
    }

    @Override
    public void executeTransaction(Payment payment) {
        strategy.executeTransaction(payment);
    }
}
