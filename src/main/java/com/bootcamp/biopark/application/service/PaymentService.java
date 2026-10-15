package com.bootcamp.biopark.application.service;


import com.bootcamp.biopark.application.port.in.CreatePaymentUseCase;
import com.bootcamp.biopark.application.port.in.GetPaymentUseCase;
import com.bootcamp.biopark.application.port.in.PaymentCommand;
import com.bootcamp.biopark.application.port.out.*;
import com.bootcamp.biopark.domain.model.*;
import com.bootcamp.biopark.infraestructure.in.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService implements CreatePaymentUseCase, GetPaymentUseCase {

    private final PaymentRepositoryPort repository;
    private final VisitorRepositoryPort visitorPort;
    private final RateRepositoryPort ratePort;
    private final EnvironmentRepositoryPort environmentPort;
    private final PaymentGatewayPort paymentGateway;
    public PaymentService(PaymentRepositoryPort paymentPort,
                          VisitorRepositoryPort visitorPort,
                          RateRepositoryPort ratePort,
                          EnvironmentRepositoryPort environmentPort,
                          PaymentGatewayPort paymentGateway) {
        this.repository = paymentPort;
        this.visitorPort = visitorPort;
        this.ratePort = ratePort;
        this.environmentPort = environmentPort;
        this.paymentGateway = paymentGateway;
    }

    @Transactional
    @Override
    public Payment processPayment(PaymentCommand command) {
        Visitor visitorFound = visitorPort.findById(command.visitorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("visitante no existe id:" + command.visitorId()));

        /*Payment payment = new Payment(null, visitorFound.getId(), (Objects.equals(command.paymentMethod(), "1"))? PaymentType.EFECTIVO:PaymentType.TARJETA);*/
        Payment payment = Payment.create(visitorFound.getId(), PaymentType.EFECTIVO);
        //CREAMOS EL LISTADO DE PAGOS
        List<PaymentItemModel> paymentItemGenerated = new ArrayList<>();
        Double totalAmount = 0.0;

        //RECORREMOS LO QUE EL CLIENTE QUIERE COMPRAR PARA CREAR LOS TICKETS
        for (PaymentCommand.PaymentItemCommand item : command.payments()) {
            Environment envFound = environmentPort.findById(item.environmentId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("entorno no existe id: " + item.environmentId()));
            Rate rateFound = ratePort.findById(item.rateId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("la tarifa no existe: " + item.rateId()));

            Double price = rateFound.getPrice();
            Integer qty = item.quantity();
            Double subtotal = price * qty;
            totalAmount += subtotal;
            //CREAMOS LISTA TICKETS
            List<Ticket> ticketsGenerados = new ArrayList<>();
            //GENERAMOS LOS TICKETS DE COMPRA
            for (int i = 0; i < item.quantity(); i++) {
                Ticket newTicket =
                        new Ticket(null, visitorFound.getId(), true, rateFound, envFound);
                ticketsGenerados.add(newTicket);

            }
            //SE CONSTRUYE EL ITEM
            PaymentItemModel paymentItem = PaymentItemModel.create(qty, price, subtotal);
            paymentItem.setTickets(ticketsGenerados);
            paymentItemGenerated.add(paymentItem);

        }
        //SE VINCULA AL PAYMENT
        payment.setItems(paymentItemGenerated);
        payment.setTotalAmount(totalAmount);
        Payment savedPayment =repository.save(payment);//obtenemos info del pago
        paymentGateway.executeTransaction(savedPayment);//ejecuta el factory
        return payment;
    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll();
    }

}