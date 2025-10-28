package co.com.thoyos.model.flight.mappers;

import co.com.thoyos.model.flight.FlightModel;
import co.com.thoyos.model.flight.dto.FlightResponseDto;

public class FlightMapper {
    public static FlightResponseDto toDto(FlightModel entity) {
        return new FlightResponseDto(entity.getSearchId(), entity.gcurrency(), entity.getFxRate(),
                FlightDetailsMapper.toDto(entity.data()));
    }
}
