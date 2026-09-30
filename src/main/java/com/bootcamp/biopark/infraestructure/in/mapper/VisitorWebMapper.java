package com.bootcamp.biopark.infraestructure.in.mapper;

import com.bootcamp.biopark.application.port.in.VisitorCommand;
import com.bootcamp.biopark.domain.model.Visitor;
import com.bootcamp.biopark.infraestructure.in.dto.request.VisitorRequestDto;
import com.bootcamp.biopark.infraestructure.in.dto.response.VisitorResponseDto;

import java.util.List;

//maper para recursos del controller
public final class VisitorWebMapper {

    private VisitorWebMapper() {
    }
    //mapeo a command
    public static VisitorCommand toCommand(VisitorRequestDto request) {
        return new VisitorCommand(
                request.name(),
                request.lastName(),
                request.dni(),
                request.age()
        );
    }
    //mapeo a mi dto
    public static VisitorResponseDto toResponse(Visitor visitor) {
        return new VisitorResponseDto(
                visitor.getId(),
                visitor.getName(),
                visitor.getLastname(),
                visitor.getDni(),
                visitor.getAge()
        );
    }

    static List<VisitorResponseDto> toResponse(List<Visitor> visitorList) {
        return visitorList.stream()
                .map(VisitorWebMapper::toResponse)
                .toList();
    }
}