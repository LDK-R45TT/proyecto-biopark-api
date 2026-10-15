package com.bootcamp.biopark.infraestructure.in.mapper;


import com.bootcamp.biopark.application.port.in.PaymentCommand;
import com.bootcamp.biopark.domain.model.Payment;
import com.bootcamp.biopark.domain.model.Ticket;
import com.bootcamp.biopark.infraestructure.in.dto.request.PaymentRequestDto;
import com.bootcamp.biopark.infraestructure.in.dto.response.PaymentResponseDto;

import java.util.List;

public final class PaymentWebMapper {

    public PaymentWebMapper() {
    }

    public static PaymentCommand toCommand(PaymentRequestDto request){
        return new PaymentCommand(
                request.visitorId(),
                "EFECTIVO",
                request.listItem().stream().map(PaymentRequestDto.PaymentItemRequest::toCommand).toList()
        );
    }
    public static PaymentResponseDto toResponse(Payment domain) {
        // 1. Transformamos los ítems del dominio al DTO del request
        List<PaymentResponseDto.Items> listItems = domain.getItems().stream()
                .map(item -> {
                    // Obtenemos el primer ticket de la lista de manera segura
                    Ticket firstTicket = item.getTickets().stream()
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("El ítem de pago no contiene tickets asociados."));

                    // Extraemos los IDs desde la relación del ticket
                    String environmentName = firstTicket.getEnvironment().getName();
                    String rateName = firstTicket.getRate().getName();
                    Integer quantity = item.getTickets().size(); // Usamos el tamaño de la lista como la cantidad real de tickets
                    return new PaymentResponseDto.Items(
                            environmentName,
                            rateName,
                            quantity
                    ); // <- Aquí faltaba el punto y coma
                }).toList();

        // 2. Construimos el PaymentResponseDto final
        return new PaymentResponseDto(
                domain.getVisitor(), // Pasamos el visitante
                listItems
                // Pasamos la lista de items que acabamos de transformar
        );
    }

}
