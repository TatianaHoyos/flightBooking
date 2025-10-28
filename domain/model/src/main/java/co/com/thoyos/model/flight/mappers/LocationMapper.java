package co.com.thoyos.model.flight.mappers;

import co.com.thoyos.model.flight.dto.FlightLocationDto;
import co.com.thoyos.model.flight.dto.LocationDto;

import java.util.ArrayList;
import java.util.List;

public class LocationMapper {
    public static LocationDto toDto(LocationModel model) {

        List<FlightLocationDto> locations = new ArrayList<>();

        model.locations().forEach((data) -> {
            locations.add(new FlightLocationDto(data.code()));
        });

        return new LocationDto(locations);
    }
}
