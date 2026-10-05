package com.bootcamp.biopark.domain.model;

public class Rate {
    private final Long id;
    private final String name;
    private final Double price;
    private final String desc;


    public Rate(Long id, String name, Double price, String desc) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }
}
