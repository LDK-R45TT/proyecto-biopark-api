package com.bootcamp.biopark.domain.model;

public class Environment {
    private final Long id;
    private final String name;
    private final String description;



    public Environment(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public static Environment register(String name, String desc){
        return new Environment(null, name, desc);

    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
