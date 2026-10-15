package com.bootcamp.biopark.domain.model;


import java.util.List;

public class PaymentItemModel {
    private final Long id;
    private final Integer quantity;
    private final Double unitPrice;
    private final Double subtotal;
    private Payment payment;
    private List<Ticket> tickets;

    public PaymentItemModel(Long id, Integer quantity, Double unitPrice, Double subtotal) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }





    public static PaymentItemModel create(Integer quantity, Double unitPrice, Double subtotal){
        return new PaymentItemModel(null, quantity, unitPrice, subtotal);
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment){
        this.payment = payment;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}

