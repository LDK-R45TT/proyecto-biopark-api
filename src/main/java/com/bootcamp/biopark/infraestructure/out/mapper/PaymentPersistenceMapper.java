package com.bootcamp.biopark.infraestructure.out.mapper;

import com.bootcamp.biopark.domain.model.Payment;
import com.bootcamp.biopark.domain.model.PaymentItemModel;
import com.bootcamp.biopark.domain.model.Ticket;
import com.bootcamp.biopark.infraestructure.entity.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentPersistenceMapper {

    private final TicketPersistenceMapper ticketMapper;

    public PaymentPersistenceMapper(TicketPersistenceMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }


    public PaymentEntity toEntity(Payment model) {
        PaymentEntity payment = new PaymentEntity();
        payment.setVisitorId(model.getVisitor());
        payment.setPaymentType(model.getPaymentType());
        payment.setTotalAmount(model.getTotalAmount());
        if (model.getItems() != null) {
            List<PaymentItem> entities = model.getItems().stream()
                    .map(itemModel -> {
                        PaymentItem itemEntity = new PaymentItem();
                        itemEntity.setQuantity(itemModel.getQuantity());
                        itemEntity.setUnitPrice(itemModel.getUnitPrice());
                        itemEntity.setSubtotal(itemModel.getSubtotal());
                        itemEntity.setPayment(payment); // Enlace bidireccional obligatorio

                        // CORRECCIÓN AQUÍ: Aseguramos todas las columnas obligatorias del Ticket
                        if (itemModel.getTickets() != null) {
                            List<TicketEntity> ticketEntities = itemModel.getTickets().stream()
                                    .map(ticketModel -> {
                                        TicketEntity te = new TicketEntity();
                                        te.setVisitorId(ticketModel.getVisitor());
                                        te.setActive(ticketModel.getActive());
                                        te.setPaymentItem(itemEntity); // Enlace al ítem (payment_item_fk)

                                        // 1. ASIGNAR ENTORNO (Evita el error Column 'environment_id_fk' cannot be null)
                                        if (ticketModel.getEnvironment() != null) {
                                            EnvironmentEntity envEntity = new EnvironmentEntity();
                                            envEntity.setEnvironmentId(ticketModel.getEnvironment().getId()); // Asignamos el ID
                                            te.setEnvironment(envEntity);
                                        }

                                        // 2. ASIGNAR TARIFA (Evita futuros errores con rate_id_fk)
                                        if (ticketModel.getRate() != null) {
                                            RateEntity rateEntity = new RateEntity();
                                            rateEntity.setRateId(ticketModel.getRate().getId()); // Asignamos el ID
                                            te.setRate(rateEntity);
                                            te.setPricePaid(ticketModel.getRate().getPrice()); // Si tu tabla guarda el precio histórico
                                        }

                                        // 3. ASIGNAR FECHA (Si aplica para la columna 'event_date')
                                        // te.setEventDate(LocalDate.now());

                                        return te;
                                    }).toList();
                            itemEntity.setTickets(ticketEntities);
                        }

                        return itemEntity;
                    }).toList();
            payment.setPaymentItems(entities);
        }
        return payment;
    }

    // 1. ESTE MÉTODO SE ENCARGA DE TRANSFORMAR EL PAGO COMPLETO
    public Payment toModel(PaymentEntity entity) {
        // Creamos el objeto principal de dominio
        Payment payment = new Payment(
                entity.getId(),
                entity.getVisitorId(),
                entity.getPaymentType(),
                entity.getGeneratedAt()
        );

        // 2. Extraemos y transformamos los ítems que vienen de la base de datos
        List<PaymentItemModel> itemsModel = new ArrayList<>();
        if (entity.getPaymentItems() != null) {
            itemsModel = entity.getPaymentItems().stream()
                    .map(entityItem -> {
                        // Convertimos los datos básicos del ítem llamando al método de abajo
                        PaymentItemModel itemModel = paymentItemToModel(entityItem);

                        // 3. Rescatamos los tickets asociados a este ítem específico usando el ticketMapper instanciado
                        if (entityItem.getTickets() != null) {
                            List<Ticket> tickets = entityItem.getTickets().stream()
                                    .map(ticketMapper::toDomain) // Transforma TicketEntity a Ticket de dominio
                                    .toList();
                            itemModel.setTickets(tickets);
                        }
                        return itemModel;
                    }).toList();
        }

        // Asignamos las listas y montos recuperados al objeto de dominio padre
        payment.setItems(itemsModel);
        payment.setTotalAmount(entity.getTotalAmount());

        return payment;
    }


    public PaymentItemModel paymentItemToModel(PaymentItem item) {
        return new PaymentItemModel(
                item.getId(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getSubtotal()
        );
    }
}
