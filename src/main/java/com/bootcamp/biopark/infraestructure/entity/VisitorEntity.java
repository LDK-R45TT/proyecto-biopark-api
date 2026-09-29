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

}