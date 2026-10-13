package com.bootcamp.biopark.infraestructure.in.controller;

import com.bootcamp.biopark.application.port.in.CreateVisitorUseCase;
import com.bootcamp.biopark.application.port.in.DeleteVisitorUseCase;
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
    private final DeleteVisitorUseCase deleteVisitorUseCase;
    public VisitorController(CreateVisitorUseCase createVisitorUseCase, GetVisitorUseCase getVisitorUseCase, SearchVisitorUseCase searchVisitorUseCase, DeleteVisitorUseCase deleteVisitorUseCase) {
        this.createVisitorUseCase = createVisitorUseCase;
        this.getVisitorUseCase = getVisitorUseCase;
        this.searchVisitorUseCase = searchVisitorUseCase;
        this.deleteVisitorUseCase = deleteVisitorUseCase;
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
        List<VisitorResponseDto> response = searchVisitorUseCase.filterBySurname(name)
                .stream().map(VisitorWebMapper::toResponse).toList();
        return  ResponseEntity.status(HttpStatus.FOUND).body(BaseResponse.success("visitante filtrado", response));
    }
    //4-crear visitante
    @PostMapping("/")
    public ResponseEntity<BaseResponse<VisitorResponseDto>> insertOne(@Valid @RequestBody VisitorRequestDto request){
        Visitor visitor = createVisitorUseCase.create(VisitorWebMapper.toCommand(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse
                .created("visitante creado", VisitorWebMapper.toResponse(visitor)));
    }
    //5-crear todos
    @PostMapping("/all")
    public ResponseEntity<BaseResponse<List<VisitorResponseDto>>> insertAll(@Valid @RequestBody List<VisitorRequestDto> request){

        List<Visitor> visitors = createVisitorUseCase.createAll(request.stream().map(VisitorWebMapper::toCommand).toList());
        List<VisitorResponseDto> responseData = visitors.stream()
                .map(VisitorWebMapper::toResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.created("listado creado", responseData));
    }
    //6-eliminar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<?>> deleteById(@PathVariable Long id){
        deleteVisitorUseCase.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success("visitante eliminado!", null));
    }
    //7-eliminar todos
    @DeleteMapping("/delete/all")
    public ResponseEntity<BaseResponse<?>> deleteAll(){
        deleteVisitorUseCase.deleteAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(BaseResponse.success("eliminados todos!", null));
    }

}
