package com.bootcamp.biopark.infraestructure.in.controller;


import com.bootcamp.biopark.application.port.in.CreateRateUseCase;
import com.bootcamp.biopark.application.port.in.GetRateUseCase;
import com.bootcamp.biopark.application.port.in.shared.BaseResponse;
import com.bootcamp.biopark.domain.model.Rate;
import com.bootcamp.biopark.infraestructure.in.dto.request.RateRequestDto;
import com.bootcamp.biopark.infraestructure.in.dto.response.RateResponseDto;
import com.bootcamp.biopark.infraestructure.in.mapper.RateWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rates")
public class RateController {

    private final CreateRateUseCase createRateUseCase;
    private final GetRateUseCase getRateUseCase;


    public RateController(CreateRateUseCase createRateUseCase, GetRateUseCase getRateUseCase) {
        this.createRateUseCase = createRateUseCase;
        this.getRateUseCase = getRateUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse<RateResponseDto>> insertOne(@Valid @RequestBody RateRequestDto request){
        Rate rate = createRateUseCase.create(RateWebMapper.toCommand(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse
                .success("tarifa filtrada", RateWebMapper.toResponse(rate)));
    }


    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<RateResponseDto>>> getAll(){
        List<RateResponseDto> response = getRateUseCase.findAll().stream()
                .map(RateWebMapper::toResponse).toList();
        return ResponseEntity.ok(BaseResponse.success(response));
    }



    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<RateResponseDto>> getById(@PathVariable("id") Long rateId){
        RateResponseDto response = RateWebMapper.toResponse(getRateUseCase.findById(rateId));
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success("tarifa encontrada!",response));
    }

}
