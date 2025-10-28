package co.com.thoyos.usecase.flight;

import co.com.thoyos.model.flight.FlightModel;
import co.com.thoyos.model.flight.FlightDetails;
import co.com.thoyos.model.flight.dto.FlightResponseDto;
import co.com.thoyos.model.flight.dto.FlightResumeDetailsDto;
import co.com.thoyos.model.flight.dto.FlightSearchInput;
import co.com.thoyos.model.flight.dto.LocationDto;
import co.com.thoyos.model.flight.exception.AverageFlightsException;
import co.com.thoyos.model.flight.exception.FlightDestinyException;
import co.com.thoyos.model.flight.gateways.FlightsGateway;
import co.com.thoyos.model.flight.mappers.FlightInputMapper;
import co.com.thoyos.model.flight.mappers.FlightMapper;
import co.com.thoyos.model.flight.utils.DateTimeFormatterConfig;
import co.com.thoyos.model.flight.utils.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class FlightUseCase {
    private final FlightsGateway flightsGateway;

   public FlightResponseDto filterFlights(final FlightSearchInput params){
      // return flightsGateway.getFlights(params);

       try{
           validateAirports(params);
           FlightSearchInput inputParams = new FlightSearchInput(params.getCurrency(), params.getDateTo(), params.getDateFrom(), params.getFlyTo(),
                   params.getFlyFrom(), params.getAirLines());
           final FlightResponseDto flightsDto = getFlights(inputParams);
           final Map<String, List<FlightDetails>> flightsAggPerDestination = groupFlightsByDestiny(flightsDto);
           final Map<String, FlightResumeDetailsDto> resume = new HashMap<>();
           flightsAggPerDestination.forEach((key,value)-> calcAvg(resume,key,value,flightsDto));
           saveRecord(inputParams);

           final FlightResponseDto flightHeaderResponseDto = new FlightResponseDto(resume,
                   DateTimeFormatterConfig.convertIsoFormat(params.getDateFrom()),
                   DateTimeFormatterConfig.convertIsoFormat(params.getDateTo()));
                  //  loggerGateway.info("Flights AVG response {} ", flightHeaderResponseDto.toString());

           return flightHeaderResponseDto;
       }catch (Exception e) {
           throw new AverageFlightsException(e);
       }
   }

    public FlightResponseDto getFlights(final FlightSearchInput inputDto){
        FlightSearchInput input = FlightInputMapper.toDto(inputDto);
        FlightModel flight = flightsGateway.getFlights(input);
        final FlightResponseDto response = FlightMapper.toDto(flight);
        //loggerGateway.info("Flights from skyPicker {} ", Objects.requireNonNull(response).toString());
        return response;
    }
    public String validateAirports(final FlightSearchInput params){
        try {

            final String[] airPorts = params.getFlyTo().trim().split(",");

            if (airPorts.length != 2) {
                throw new FlightDestinyException(
                        "The flight codes is invalid. Please insert TWO AIRPORT CODES separated by commas, "
                                + "example: (OPO,LIS) or (LIS,OPO) to fetch data from PORTO and LISBON flights. "
                                + "Consult link: https://airportcodes.aero/iata/ and see if the codes are valid.");
            }

            final String locationCodeOne = StringUtils.replaceSpecialChars(airPorts[0]);
            final String locationCodeTwo = StringUtils.replaceSpecialChars(airPorts[1]);
            final String locations = locationCodeOne.concat(",").concat(locationCodeTwo);
            final LocationDto locationOne = LocationMapper.toDto(flightsGateway.getLocation(locationCodeOne));
            final LocationDto locationTwo = LocationMapper.toDto(flightsGateway.getLocation(locationCodeTwo));

            if (Objects.requireNonNull(locationOne).locations().size() == 0
                    || Objects.requireNonNull(locationTwo).locations().size() == 0) {
                throw new FlightDestinyException("At least one of the airport codes are invalid. "
                        + "Consult link: https://airportcodes.aero/iata/ and see if the codes are valid.");
            }

            return locations;

        } catch (final Exception e) {
            throw new FlightDestinyException(
                    "The flight codes is invalid. Please insert two airport codes separated by commas,"
                            + " example: (OPO,LIS) or (LIS,OPO) to fetch data from PORTO and LISBON flights. "
                            + "Consult link: https://airportcodes.aero/iata/ and see if the codes are valid.", e);
        }
    }

    public Map<String, List<FlightDetails>> groupFlightsByDestiny(final FlightResponseDto dto){

    }
    public void calcAvg(final Map<String, FlightResumeDetailsDto> res, final String destination,
                        final List<FlightDetails> flights, final FlightResponseDto flightsDto) throws AverageFlightsException {

    }
    public void saveRecord(final FlightSearchInput params){

    }

}
