package com.bootcamp.biopark.domain.model;

/*business logic model for service*/
public class Visitor {
    private final Long id;
    private final String name;
    private final String lastname;
    private final String dni;
    private final Integer age;


    public Visitor(Long id, String name, String lastname, String dni, Integer age) {
        //validaciones del dominio
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("el nombre del visitante no puede estar vacío");
        }
        if (age == null || age < 0) {
            throw new IllegalArgumentException("la edad no puede ser negativa");
        }
        if (dni == null || dni.length() != 8) {
            throw new IllegalArgumentException("el DNI de dominio debe tener 8 caracteres");
        }
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }


    public String getLastname() {
        return lastname;
    }

    public Integer getAge() {
        return age;
    }
    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}
