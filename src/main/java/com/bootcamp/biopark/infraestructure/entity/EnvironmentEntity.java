package com.bootcamp.biopark.infraestructure.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "environments")
public class EnvironmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long environmentId;
    String name;
    String description;
    @OneToMany(mappedBy = "environment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<String> tickets = new HashSet<>();
    public EnvironmentEntity() {
    }

    public EnvironmentEntity(Long environmentId, String name, String description) {
        this.environmentId = environmentId;
        this.name = name;
        this.description = description;
    }

    public Long getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Long environmentId) {
        this.environmentId = environmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

