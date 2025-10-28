package co.com.thoyos.model.flight.gateways;

import co.com.thoyos.model.flight.FlightModel;
import co.com.thoyos.model.flight.dto.FlightSearchInput;

public interface FlightsGateway {
    FlightModel getFlights(FlightSearchInput input);
}
