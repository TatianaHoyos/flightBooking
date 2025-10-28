package co.com.thoyos.model.flight.mappers;

import co.com.thoyos.model.flight.dto.FlightSearchInput;

public class FlightInputMapper {

    public static FlightSearchInput toDto(FlightSearchInput inputDto) {

        return new FlightSearchInput(inputDto.getCurrency(), inputDto.getDateFrom(), inputDto.getDateTo(),
                inputDto.getFlyTo(), inputDto.getFlyFrom(), inputDto.getAirLines());
    }
}
