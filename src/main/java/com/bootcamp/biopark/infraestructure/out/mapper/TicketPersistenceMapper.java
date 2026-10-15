package com.bootcamp.biopark.infraestructure.out.mapper;


import com.bootcamp.biopark.domain.model.Ticket;
import com.bootcamp.biopark.infraestructure.entity.EnvironmentEntity;
import com.bootcamp.biopark.infraestructure.entity.RateEntity;
import com.bootcamp.biopark.infraestructure.entity.TicketEntity;
import com.bootcamp.biopark.infraestructure.entity.VisitorEntity;
import org.springframework.stereotype.Component;

@Component
public class TicketPersistenceMapper {

    private final RatePersistenceMapper rateMapper;
    private final EnvironmentPersistenceMapper environmentMapper;

    public TicketPersistenceMapper(RatePersistenceMapper rateMapper, EnvironmentPersistenceMapper environmentMapper) {
        this.rateMapper = rateMapper;
        this.environmentMapper = environmentMapper;
    }


    public TicketEntity toEntity(Ticket model){

        if (model == null) return null;
        TicketEntity entity = new TicketEntity();
        entity.setVisitorId(model.getVisitor());

        // Mapeo directo y seguro porque el dominio ya garantizó que no son nulos
        VisitorEntity visitorRef = new VisitorEntity();
        visitorRef.setVisitorId(model.getVisitor());
        entity.setVisitorId(visitorRef.getVisitorId());

        EnvironmentEntity envRef = new EnvironmentEntity();
        envRef.setEnvironmentId(model.getEnvironment().getId());
        entity.setEnvironment(envRef);

        RateEntity rateRef = new RateEntity();
        rateRef.setRateId(model.getId());
        entity.setRate(rateRef);

        return entity;
    }

    public Ticket toDomain(TicketEntity entity){

        if (entity == null) return null;
        return new Ticket(
                entity.getTicketId(),
                entity.getVisitorId(),
                entity.getActive(),
                rateMapper.toModel(entity.getRate()),
                environmentMapper.toModel(entity.getEnvironment())
        );
    }
}
