package com.bootcamp.biopark.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long ticketId;
    @Column(name = "visitor_id_fk")
    private Long visitorId;
    @Column(name = "ticket_active", nullable = false)
    private Boolean active = true;
    @Column(name = "price", nullable = false)
    private Double pricePaid;
    @Column(name = "event_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    @ManyToOne
    @JoinColumn(name = "rate_id_fk", nullable = false)
    private RateEntity rate;
    @ManyToOne
    @JoinColumn(name = "environment_id_fk", nullable = false)
    private EnvironmentEntity environment;
    @ManyToOne
    @JoinColumn(name = "payment_item_fk")
    private PaymentItem paymentItem;


    public TicketEntity() {
    }

    public TicketEntity(Long visitorId, Double pricePaid, LocalDate eventDate, RateEntity rate, EnvironmentEntity environment) {
        this.visitorId = visitorId;
        this.pricePaid = pricePaid;
        this.eventDate = eventDate;
        this.rate = rate;
        this.environment = environment;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public RateEntity getRate() {
        return rate;
    }

    public void setRate(RateEntity rate) {
        this.rate = rate;
    }

    public EnvironmentEntity getEnvironment() {
        return environment;
    }

    public void setEnvironment(EnvironmentEntity environment) {
        this.environment = environment;
    }

    public void setPaymentItem(PaymentItem paymentItem) {
        this.paymentItem = paymentItem;
    }

    public void setPricePaid(Double pricePaid) {
        this.pricePaid = pricePaid;
    }
}
