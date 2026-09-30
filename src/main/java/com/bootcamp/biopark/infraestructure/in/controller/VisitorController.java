package com.bootcamp.biopark.infraestructure.in.controller;

import com.bootcamp.biopark.application.port.in.CreateVisitorUseCase;
import com.bootcamp.biopark.application.port.in.GetVisitorUseCase;
import com.bootcamp.biopark.application.port.in.SearchVisitorUseCase;
import com.bootcamp.biopark.application.port.in.shared.BaseResponse;
import com.bootcamp.biopark.domain.model.Visitor;
import com.bootcamp.biopark.infraestructure.in.dto.request.VisitorRequestDto;
import com.bootcamp.biopark.infraestructure.in.dto.response.VisitorResponseDto;
import com.bootcamp.biopark.infraestructure.in.mapper.VisitorWebMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visitors")
public class VisitorController {

    private final CreateVisitorUseCase createVisitorUseCase;
    private final GetVisitorUseCase getVisitorUseCase;
    private final SearchVisitorUseCase searchVisitorUseCase;

    public VisitorController(CreateVisitorUseCase createVisitorUseCase, GetVisitorUseCase getVisitorUseCase, SearchVisitorUseCase searchVisitorUseCase) {
        this.createVisitorUseCase = createVisitorUseCase;
        this.getVisitorUseCase = getVisitorUseCase;
        this.searchVisitorUseCase = searchVisitorUseCase;
    }

    //1-obtener todos
    @GetMapping("/all")
    public ResponseEntity<BaseResponse<List<VisitorResponseDto>>> getAll(){
        List<VisitorResponseDto> response = getVisitorUseCase.findAll().stream()
                .map(VisitorWebMapper::toResponse).toList();
        return ResponseEntity.ok(BaseResponse.success(response));
    }


    //2-obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<VisitorResponseDto>> getById(@PathVariable("id") Long visitorId){
        VisitorResponseDto response = VisitorWebMapper.toResponse(getVisitorUseCase.findById(visitorId));
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.success("visitante encontrado!",response));
    }

    //3-obtener por nombre
    @GetMapping("/por-nombre")
    public ResponseEntity<BaseResponse<List<VisitorResponseDto>>> getByName(@RequestParam String name){
        List<VisitorResponseDto> response = searchVisitorUseCase.findBySurname(name)
                .stream().map(VisitorWebMapper::toResponse).toList();
        return  ResponseEntity.status(HttpStatus.FOUND).body(BaseResponse.success("visitante filtrado", response));
    }
    //4-crear visitante
    @PostMapping("/")
    public ResponseEntity<BaseResponse<VisitorResponseDto>> insertOne(@Valid @RequestBody VisitorRequestDto request){
        Visitor visitor = createVisitorUseCase.create(VisitorWebMapper.toCommand(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse
                .success("visitante filtrado", VisitorWebMapper.toResponse(visitor)));
    }



}
