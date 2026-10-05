package com.bootcamp.biopark.infraestructure.in.controller;

import com.bootcamp.biopark.application.port.in.CreateEnvironmentUseCase;
import com.bootcamp.biopark.application.port.in.GetEnvironmentUseCase;
import com.bootcamp.biopark.application.port.in.shared.BaseResponse;
import com.bootcamp.biopark.domain.model.Environment;
import com.bootcamp.biopark.infraestructure.in.dto.request.EnvironmentRequestDto;
import com.bootcamp.biopark.infraestructure.in.dto.response.EnvironmentResponseDto;
import com.bootcamp.biopark.infraestructure.in.mapper.EnvironmentWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/environments")
public class EnvironmentController {

    private final CreateEnvironmentUseCase createEnvironmentUseCase;
    private final GetEnvironmentUseCase getEnvironmentUseCase;
    public EnvironmentController(CreateEnvironmentUseCase createEnvironmentUseCase, GetEnvironmentUseCase getEnvironmentUseCase) {
        this.createEnvironmentUseCase = createEnvironmentUseCase;
        this.getEnvironmentUseCase = getEnvironmentUseCase;
    }

    @PostMapping("/")
    public ResponseEntity<BaseResponse<EnvironmentResponseDto>> insertOne(@Valid @RequestBody EnvironmentRequestDto request){
        Environment command = createEnvironmentUseCase.create(EnvironmentWebMapper.toCommand(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse
                .success("recinto filtrado", EnvironmentWebMapper.toResponse(command)));
    }



    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<EnvironmentResponseDto>>> getAll(){
        List<EnvironmentResponseDto> response = getEnvironmentUseCase.findAll().stream()
                .map(EnvironmentWebMapper::toResponse).toList();
        return ResponseEntity.ok(BaseResponse.success(response));
    }



    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<EnvironmentResponseDto>> getById(@PathVariable("id") Long envId){
        EnvironmentResponseDto response = EnvironmentWebMapper.toResponse(getEnvironmentUseCase.findById(envId));
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success("recinto encontrado!",response));
    }

}
