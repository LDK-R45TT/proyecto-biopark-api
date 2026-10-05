package com.bootcamp.biopark.infraestructure.in.mapper;

import com.bootcamp.biopark.application.port.in.RateCommand;
import com.bootcamp.biopark.domain.model.Rate;
import com.bootcamp.biopark.infraestructure.in.dto.request.RateRequestDto;
import com.bootcamp.biopark.infraestructure.in.dto.response.RateResponseDto;

import java.util.List;

public final class RateWebMapper {
    public RateWebMapper() {
    }

    public static RateCommand toCommand(RateRequestDto request){
        return new RateCommand(
                request.name(),
                request.price(),
                request.description()
        );
    }
    public static RateResponseDto toResponse(Rate response) {
        return new RateResponseDto(
                response.getId(),
                response.getName(),
                response.getPrice(),
                response.getDesc()
        );
    }

    public static List<RateResponseDto> toResponse(List<Rate> rateList) {
        return rateList.stream()
                .map(RateWebMapper::toResponse)
                .toList();
    }
}
