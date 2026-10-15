package com.bootcamp.biopark.infraestructure.entity;

import com.bootcamp.biopark.domain.model.PaymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payments")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "visitor_id")
    private Long visitorId;
    private Double totalAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentType paymentType;
    @Column(name = "payment_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate generatedAt;
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PaymentItem> paymentItems = new ArrayList<>();

    public PaymentEntity() {
    }

    public PaymentEntity(Long visitorId, PaymentType paymentType, LocalDate generatedAt) {
        this.visitorId = visitorId;
        this.paymentType = paymentType;
        this.generatedAt = generatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDate generatedAt) {
        this.generatedAt = generatedAt;
    }

    public List<PaymentItem> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<PaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
    }
}
