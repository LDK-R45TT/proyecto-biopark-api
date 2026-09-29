package com.bootcamp.biopark.infraestructure.entity;

import jakarta.persistence.*;

@Entity//clase persistente
@Table(name = "visitors")//table name in db
public class VisitorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long visitorId;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String surname;
    private String dni;
    @Column(name = "edad")
    private Integer age;
    //constructor vacio
    public VisitorEntity() {
    }

    public VisitorEntity(String name, String surname, String dni, Integer age) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.age = age;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}