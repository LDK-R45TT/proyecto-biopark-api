package com.bootcamp.biopark.infraestructure.in.dto.request;


import com.bootcamp.biopark.application.port.in.PaymentCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record PaymentRequestDto(
        @NotBlank(message = "Se requiere un visitante")
        Long visitorId,
        List<PaymentItemRequest> listItem

) {
    public record PaymentItemRequest(
            @NotBlank(message = "Se requiere un recinto")
            Long environmentId,
            @NotBlank(message = "Se requiere una tarifa")
            Long rateId,
            @Positive
            @NotBlank(message = "se requiere una cantidad")
            Integer quantity
    ){

        public PaymentCommand.PaymentItemCommand toCommand(){
            return new PaymentCommand.PaymentItemCommand(
                    environmentId,
                    rateId,
                    quantity
            );
        }
    }
}