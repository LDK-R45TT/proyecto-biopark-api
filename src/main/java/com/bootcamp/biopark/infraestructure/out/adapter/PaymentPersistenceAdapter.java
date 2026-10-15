package com.bootcamp.biopark.infraestructure.out.adapter;


import com.bootcamp.biopark.application.port.out.PaymentRepositoryPort;
import com.bootcamp.biopark.domain.model.Payment;
import com.bootcamp.biopark.infraestructure.entity.PaymentEntity;
import com.bootcamp.biopark.infraestructure.out.mapper.PaymentPersistenceMapper;
import com.bootcamp.biopark.infraestructure.out.repository.PaymentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaymentPersistenceAdapter implements PaymentRepositoryPort {

    private final PaymentJpaRepository repository;
    private final PaymentPersistenceMapper paymentMapper;

    public PaymentPersistenceAdapter(PaymentJpaRepository paymentRepository, PaymentPersistenceMapper paymentMapper) {
        this.repository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity paymentSaved = repository.save(paymentMapper.toEntity(payment));
        return paymentMapper.toModel(paymentSaved);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        Optional<PaymentEntity> paymentFound = repository.findById(id);
        return paymentFound.map(paymentMapper::toModel);

    }

    @Override
    public List<Payment> findAll() {
        return repository.findAll().stream().map(paymentMapper::toModel).toList();
    }

    @Override
    public List<Payment> filterByVisitor(Long visitorId) {
        return repository.filterPaymentsbyVisitor(visitorId)
                .stream()
                .map(paymentMapper::toModel).toList();
    }
}
