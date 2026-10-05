package com.bootcamp.biopark.infraestructure.out.mapper;

import com.bootcamp.biopark.domain.model.Environment;
import com.bootcamp.biopark.infraestructure.entity.EnvironmentEntity;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentPersistenceMapper {

    public EnvironmentEntity toEntity(Environment model){

        EnvironmentEntity entity = new EnvironmentEntity();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        return entity;
    }

    public Environment toModel(EnvironmentEntity entity){
        return new Environment(
                entity.getEnvironmentId(),
                entity.getName(),
                entity.getDescription()
        );
    }

}
