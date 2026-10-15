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
    private Long environmentId;
    private String name;
    private String description;
    @Column(name = "active", nullable = false)
    private Boolean active = true;
    @OneToMany(mappedBy = "environment", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    Set<TicketEntity> tickets = new HashSet<>();
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

