package com.bootcamp.biopark.domain.model;

public class Environment {
    private final Long id;
    private final String name;
    private final String description;
    private final Boolean active;



    public Environment(Long id, String name, String description, Boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }
    public static Environment register(String name, String desc){
        return new Environment(null, name, desc, true);

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

    public Boolean getActive() {
        return active;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
