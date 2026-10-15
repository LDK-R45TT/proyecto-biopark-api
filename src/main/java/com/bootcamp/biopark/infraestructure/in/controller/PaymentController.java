package com.bootcamp.biopark.infraestructure.in.controller;


import com.bootcamp.biopark.application.port.in.CreatePaymentUseCase;
import com.bootcamp.biopark.application.port.in.GetPaymentUseCase;
import com.bootcamp.biopark.application.port.in.shared.BaseResponse;
import com.bootcamp.biopark.domain.model.Payment;
import com.bootcamp.biopark.infraestructure.in.dto.request.PaymentRequestDto;
import com.bootcamp.biopark.infraestructure.in.dto.response.PaymentResponseDto;
import com.bootcamp.biopark.infraestructure.in.mapper.PaymentWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    private final CreatePaymentUseCase createPaymentUseCase;
    private final GetPaymentUseCase getPaymentUseCase;

    public PaymentController(CreatePaymentUseCase createPaymentUseCase, GetPaymentUseCase getPaymentUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.getPaymentUseCase = getPaymentUseCase;
    }


    @PostMapping("/")
    public ResponseEntity<BaseResponse<PaymentResponseDto>> insertOne(@RequestBody PaymentRequestDto request){
        Payment payment = createPaymentUseCase.processPayment(PaymentWebMapper.toCommand(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse
                .success("pago existoso", PaymentWebMapper.toResponse(payment)));
    }


    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<PaymentResponseDto>>> getAll(){
        List<PaymentResponseDto> response = getPaymentUseCase.findAll().stream()
                .map(PaymentWebMapper::toResponse).toList();
        return ResponseEntity.ok(BaseResponse.success(response));
    }
}
