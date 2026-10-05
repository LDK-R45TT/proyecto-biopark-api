package com.bootcamp.biopark.infraestructure.in.mapper;

import com.bootcamp.biopark.application.port.in.EnvironmentCommand;
import com.bootcamp.biopark.domain.model.Environment;
import com.bootcamp.biopark.infraestructure.in.dto.request.EnvironmentRequestDto;
import com.bootcamp.biopark.infraestructure.in.dto.response.EnvironmentResponseDto;

import java.util.List;

public final class EnvironmentWebMapper {

    public EnvironmentWebMapper() {
    }

    public static EnvironmentCommand toCommand(EnvironmentRequestDto request){
        return new EnvironmentCommand(
                request.name(),
                request.description()
        );
    }
    public static EnvironmentResponseDto toResponse(Environment model) {
        return new EnvironmentResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription()
        );
    }

    public static List<EnvironmentResponseDto> toResponse(List<Environment> envList) {
        return envList.stream()
                .map(EnvironmentWebMapper::toResponse)
                .toList();
    }
}
