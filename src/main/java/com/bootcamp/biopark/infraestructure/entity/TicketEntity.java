package com.bootcamp.biopark.infraestructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long ticketId;
    @Column(name = "visitor_id_fk")
    Long visitorId;
    @Column(name = "ticket_active")
    Boolean active;
    @Column(name = "rate_id_fk")
    Long rateId;
    @ManyToOne
    @JoinColumn(name = "environment_id_fk", nullable = false)
    EnvironmentEntity environment;
}
