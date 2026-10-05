package com.bootcamp.biopark.infraestructure.out.mapper;

import com.bootcamp.biopark.domain.model.Rate;
import com.bootcamp.biopark.infraestructure.entity.RateEntity;
import org.springframework.stereotype.Component;

@Component
public class RatePersistenceMapper {

    public RateEntity toEntity(Rate model){

        RateEntity rate = new RateEntity();
        rate.setName(model.getName());
        rate.setDescription(model.getDesc());
        rate.setPrice(model.getPrice());
        return rate;
    }

    public Rate toModel(RateEntity entity){
        return new Rate(entity.getRateId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDescription()
        );
    }
}
