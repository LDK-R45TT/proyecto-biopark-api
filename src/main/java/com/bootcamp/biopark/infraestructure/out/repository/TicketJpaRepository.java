package com.bootcamp.biopark.infraestructure.out.repository;

import com.bootcamp.biopark.infraestructure.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepository extends JpaRepository<TicketEntity, Long> {
}
