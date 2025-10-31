package co.com.thoyos.model.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class LocationEntity {
    private List<FlightLocationEntity> locations;
}
