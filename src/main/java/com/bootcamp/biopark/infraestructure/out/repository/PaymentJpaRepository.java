package com.bootcamp.biopark.infraestructure.out.repository;


import com.bootcamp.biopark.infraestructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
    @Query(value = """
        SELECT p.*
        FROM payments p
        WHERE p.visitor_id = :idVisitor
        """, nativeQuery = true)
    List<PaymentEntity> filterPaymentsbyVisitor(@Param("idVisitor") Long id);
}
