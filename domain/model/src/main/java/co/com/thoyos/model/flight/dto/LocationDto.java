package co.com.thoyos.model.flight.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class LocationDto {
    private List<FlightLocationDto> locations;
}
