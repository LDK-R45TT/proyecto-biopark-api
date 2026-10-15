package com.bootcamp.biopark.domain.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Payment {
    private final Long id;
    private final Long visitor;
    private Double totalAmount;
    private final PaymentType paymentType;
    private final LocalDate generated;
    private List<PaymentItemModel> items= new ArrayList<>();


    private Payment(Long id, Long visitor, PaymentType paymentType, LocalDate generated, List<PaymentItemModel> items) {
        this.id = id;
        this.visitor = visitor;
        this.paymentType = paymentType;
        this.generated = generated;
        this.items = items != null ? List.copyOf(items) : Collections.emptyList();

        // El totalAmount se calcula automáticamente sobre la marcha aquí dentro
        this.totalAmount = calculateTotal(this.items);
    }
    public Payment(Long id, Long visitor, PaymentType paymentType, LocalDate generated) {
        this.id = id;
        this.visitor = visitor;
        this.paymentType = paymentType;
        this.generated = generated;
    }
    public static Payment create(Long visitor, PaymentType paymentType) {
        LocalDate now = LocalDate.now();
        return new Payment(null, visitor, paymentType,  now);
    }
    private Double calculateTotal(List<PaymentItemModel> paymentItems) {
        return paymentItems.stream()
                .mapToDouble(PaymentItemModel::getSubtotal) // Asumiendo que PaymentItem tiene getSubtotal()
                .sum();
    }


    public Long getId() {
        return id;
    }

    public Long getVisitor() {
        return visitor;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setItems(List<PaymentItemModel> items) {
        this.items = items;
    }



    public PaymentType getPaymentType() {
        return paymentType;
    }

    public List<PaymentItemModel> getItems() {
        return items;
    }
}
