package com.bootcamp.biopark.domain.model;

/*business logic model for service*/
public class Visitor {
    private final Long id;
    private final String name;
    private final String surname;
    private final String lastname;
    private final Integer age;


    public Visitor(Long id, String name, String surname, String lastname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}
