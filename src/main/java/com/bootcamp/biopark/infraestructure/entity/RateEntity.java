package com.bootcamp.biopark.infraestructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rates")
public class RateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long rateId;
    @Column(name = "nombre")
    private String name;
    @Column(name = "precio")
    private Double price;
    @Column(name = "descripcion")
    private String description;

    public RateEntity() {
    }

    public RateEntity( String name, Double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
