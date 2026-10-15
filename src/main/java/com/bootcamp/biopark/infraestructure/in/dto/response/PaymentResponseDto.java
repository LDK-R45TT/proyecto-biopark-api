package com.bootcamp.biopark.infraestructure.in.dto.response;


import java.util.List;

public record PaymentResponseDto(
        Long visitorId,
        List<Items> items

) {

    public record Items(
            String environment,
            String rate,
            Integer quantity
    ){}
}
