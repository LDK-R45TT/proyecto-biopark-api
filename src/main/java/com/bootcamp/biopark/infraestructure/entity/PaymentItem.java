package com.bootcamp.biopark.infraestructure.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "payment_items")
public class PaymentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @Column(name = "unit_price")
    private Double unitPrice;
    @Column(name = "subtotal")
    private Double subtotal;
    @ManyToOne
    @JoinColumn(name = "payment_id_fk", nullable = false)
    private PaymentEntity payment;
    @OneToMany(mappedBy = "paymentItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TicketEntity> tickets;


    public PaymentItem() {
    }

    public PaymentItem(Integer quantity, Double unitPrice, Double subtotal) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }
}
