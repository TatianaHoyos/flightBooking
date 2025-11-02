package co.com.thoyos.restservice;

import co.com.thoyos.model.flight.*;
import co.com.thoyos.model.flight.dto.FlightSearchInput;
import co.com.thoyos.model.flight.gateways.FlightsGateway;
import co.com.thoyos.restservice.dto.FlightDetailsWebResponse;
import co.com.thoyos.restservice.dto.FlightWebResponse;
import co.com.thoyos.restservice.util.HttpUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RestServiceAdapter implements FlightsGateway {

    @Autowired
    private final HttpUtility httpUtility;
    private HttpHeaders httpHeaders;

    @Override
    public FlightEntity getFlights(FlightSearchInput input) {
        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        final Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("fly_from", input.getFlyFrom());
        mapParams.put("fly_to", input.getFlyTo());
        mapParams.put("currency", input.getCurrency());
        mapParams.put("date_from", input.getDateFrom());
        mapParams.put("date_to", input.getDateTo());
        mapParams.put("select_airlines", input.getAirLines());

        ResponseEntity<FlightWebResponse> response = httpUtility.getForEntity("http://localhost:3000/flights?fly_from={flyFrom}&fly_to={flyTo}" +
                "&currency={currency}&date_from={dateFrom}&date_to={dateTo}&select_airlines={selectAirlines}",httpHeaders, FlightWebResponse.class,mapParams);
        FlightEntity entity = buildFlights(response);

        return entity;
    }

    @Override
    public LocationEntity getLocation(String iata) {
        return null;
    }


    public FlightEntity buildFlights(ResponseEntity<FlightWebResponse> response) {
        FlightWebResponse flight = response.getBody();
        List<FlightDetailsEntity> data = new ArrayList<>();
        for (FlightDetailsWebResponse detail : flight.getData()){
            data.add(FlightDetailsEntity.builder()
                    .id(detail.getId())
                    .aTime(detail.getATime())
                    .aTimeFormatted(detail.getATimeFormatted())
                    .aTimeUTC(detail.getATimeUTC())
                    .baggage(FlightBagPriceEntity.builder()
                            .bagOnePrice(detail.getBaggage().getBagOnePrice())
                            .bagTwoPrice(detail.getBaggage().getBagTwoPrice())
                            .build())
                    .cityCodeFrom(detail.getCityCodeFrom())
                    .cityFrom(detail.getCityFrom())
                    .dTime(detail.getDTime())
                    .cityCodeTo(detail.getCityCodeTo())
                    .dTimeFormatted(detail.getDTimeFormatted())
                    .flyFrom(detail.getFlyFrom())
                    .dTimeUTC(detail.getDTimeUTC())
                    .price(detail.getPrice())
                    .cityTo(detail.getCityTo())
                    .flyTo(detail.getFlyTo())

                    .build());
        }
        return FlightEntity.builder()
                .fxRate(flight.getFxRate())
                .searchId(flight.getSearchId())
                .currency(flight.getCurrency())
                .data(data)
                .build();
    }


    public LocationEntity buildLocation(String iata) {
        return LocationEntity.builder()
                .locations(Collections.singletonList(FlightLocationEntity.builder()
                        .code("55")
                        .build()))
                .build();
    }
}
