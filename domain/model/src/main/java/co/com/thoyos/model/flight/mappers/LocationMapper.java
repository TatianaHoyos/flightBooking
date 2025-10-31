package co.com.thoyos.model.flight.mappers;

import co.com.thoyos.model.flight.LocationEntity;
import co.com.thoyos.model.flight.dto.FlightLocationDto;
import co.com.thoyos.model.flight.dto.LocationDto;

import java.util.ArrayList;
import java.util.List;

public class LocationMapper {
    public static LocationDto toDto(LocationEntity model) {

        List<FlightLocationDto> locations = new ArrayList<>();

        model.getLocations().forEach((data) -> {
            locations.add(new FlightLocationDto(data.getCode()));
        });

        return new LocationDto(locations);
    }
}
