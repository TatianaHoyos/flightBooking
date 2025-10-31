package co.com.thoyos.usecase.flight;

import co.com.thoyos.model.flight.FlightEntity;
import co.com.thoyos.model.flight.FlightDetailsEntity;
import co.com.thoyos.model.flight.dto.FlightResumeDetailsOutput;
import co.com.thoyos.model.flight.dto.FlightSearchInput;
import co.com.thoyos.model.flight.dto.LocationDto;
import co.com.thoyos.model.flight.exception.AverageFlightsException;
import co.com.thoyos.model.flight.exception.FlightDestinyException;
import co.com.thoyos.model.flight.gateways.FlightsGateway;
import co.com.thoyos.model.flight.mappers.FlightInputMapper;
import co.com.thoyos.model.flight.mappers.LocationMapper;
import co.com.thoyos.model.flight.utils.NumberUtilsConfig;
import co.com.thoyos.model.flight.utils.StringUtils;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
public class FlightUseCase {
    private final FlightsGateway flightsGateway;

   public Map<String, FlightResumeDetailsOutput> filterFlights(final FlightSearchInput params){
      try{
           validateAirports(params);
           FlightSearchInput inputParams = new FlightSearchInput(params.getCurrency(), params.getDateTo(), params.getDateFrom(), params.getFlyTo(),
                   params.getFlyFrom(), params.getAirLines());
           final FlightEntity flightsDto = getFlights(inputParams);
           final Map<String, List<FlightDetailsEntity>> flightsAggPerDestination = groupFlightsByDestiny(flightsDto);
           final Map<String, FlightResumeDetailsOutput> resume = new HashMap<>();
           flightsAggPerDestination.forEach((key,value)-> calcAvg(resume,key,value,flightsDto));
           //saveRecord(inputParams);

           //  loggerGateway.info("Flights AVG response {} ", flightHeaderResponseDto.toString());
            return resume;
       }catch (Exception e) {
           throw new AverageFlightsException(e);
       }
   }

    public FlightEntity getFlights(final FlightSearchInput inputDto){
        FlightSearchInput input = FlightInputMapper.toDto(inputDto);
        FlightEntity flight = flightsGateway.getFlights(input);
        //final FlightResponseDto response = FlightMapper.toDto(flight);
        //loggerGateway.info("Flights from skyPicker {} ", Objects.requireNonNull(response).toString());
        return flight;
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

            if (Objects.requireNonNull(locationOne).getLocations().size() == 0
                    || Objects.requireNonNull(locationTwo).getLocations().size() == 0) {
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

    public Map<String, List<FlightDetailsEntity>> groupFlightsByDestiny(FlightEntity dto){
        return dto.getData().stream().collect(groupingBy(FlightDetailsEntity::getFlyTo));
    }

    public void calcAvg(final Map<String, FlightResumeDetailsOutput> res, final String destination,
                        final List<FlightDetailsEntity> flights, final FlightEntity flightsDto)
            throws AverageFlightsException {
        try {
            final FlightDetailsEntity flyDetails = flights.stream().findFirst().get();
            final Double priceAvg = flights.stream().collect(Collectors.averagingDouble(FlightDetailsEntity::getPrice));
            final Double bagOneAvg = flights.stream().collect(Collectors.averagingDouble(p -> p.getBaggage().getBagOnePrice()));
           /* final Double bagOneAvg = flights.stream().collect(Collectors.averagingDouble(
                    p -> (p.getBaggage().getBagOnePrice() == null ?
                            0.00 :
                            p.getBaggage().getBagOnePrice())));*/
            final Double bagTwoAvg = flights.stream().collect(Collectors.averagingDouble(p -> p.getBaggage().getBagTwoPrice()));
            final FlightResumeDetailsOutput.FlightBagPriceAverageDto bagsAverage =
                    new FlightResumeDetailsOutput.FlightBagPriceAverageDto(NumberUtilsConfig.round(bagOneAvg), NumberUtilsConfig.round(bagTwoAvg));
            final FlightResumeDetailsOutput detailsFlyTo = new FlightResumeDetailsOutput(flyDetails.getCityTo(),
                    flightsDto.getCurrency(), NumberUtilsConfig.round(priceAvg), bagsAverage);
            res.put(destination, detailsFlyTo);

        } catch (Exception e) {
            throw new AverageFlightsException(e);
        }
    }
//    public void saveRecord(final FlightSearchInput params){
//        final FlightLogModel record = new FlightLogModel(params.flyTo(), params.currency(), params.dateTo(), params.dateFrom(), LocalDateTime.now());
//        flightsLogsGateway.create(record);
//    }

}
