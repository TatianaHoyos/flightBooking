package co.com.thoyos.model.flight.gateways;

import co.com.thoyos.model.flight.FlightEntity;
import co.com.thoyos.model.flight.LocationEntity;
import co.com.thoyos.model.flight.dto.FlightSearchInput;

public interface FlightsGateway {
    FlightEntity getFlights(FlightSearchInput input);
    LocationEntity getLocation(String iata);
}
