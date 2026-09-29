package com.bootcamp.biopark.infraestructure.out.mapper;

import com.bootcamp.biopark.domain.model.Visitor;
import com.bootcamp.biopark.infraestructure.entity.VisitorEntity;

public class VisitorPersistenceMapper {

    public VisitorEntity toEntity(Visitor model){

        VisitorEntity visitor = new VisitorEntity();
        visitor.setName(model.getName());
        visitor.setSurname(model.getLastname());
        visitor.setDni(model.getDni());
        visitor.setAge(model.getAge());

        return visitor;



    }


    public Visitor toModel(VisitorEntity visitor){

        return new Visitor(visitor.getVisitorId(),
                visitor.getName(), visitor.getSurname(),
                visitor.getDni(),
                visitor.getAge()
        );
    }
}
