package co.com.thoyos.model.flight.gateways;

import co.com.thoyos.model.flight.Flight;
import co.com.thoyos.model.flight.dto.FlightSearchInput;

public interface FlightsGateway {
    Flight getFlights(FlightSearchInput input);
}
