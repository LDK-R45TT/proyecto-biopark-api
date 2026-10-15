package com.bootcamp.biopark.application.port.in;

import java.util.List;

public record PaymentCommand(
        Long visitorId,
        String paymentMethod,
        List<PaymentItemCommand> payments
) {
    public record PaymentItemCommand(
            Long environmentId,
            Long rateId,
            Integer quantity
    ){



    }
}
