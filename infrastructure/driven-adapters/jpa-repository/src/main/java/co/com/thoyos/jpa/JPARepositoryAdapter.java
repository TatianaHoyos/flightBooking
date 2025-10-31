package co.com.thoyos.jpa;

import co.com.thoyos.jpa.helper.AdapterOperations;
import co.com.thoyos.model.flight.*;
import co.com.thoyos.model.flight.dto.FlightSearchInput;
import co.com.thoyos.model.flight.gateways.FlightsGateway;
import lombok.AllArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collections;

//@Repository
//public class JPARepositoryAdapter extends AdapterOperations<Object/* change for domain model */, Object/* change for adapter model */, String, JPARepository>
//// implements ModelRepository from domain
//{
//
//    public JPARepositoryAdapter(JPARepository repository, ObjectMapper mapper) {
//        /**
//         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
//         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
//         *  Or using mapper.map with the class of the object model
//         */
//        super(repository, mapper, d -> mapper.map(d, Object.class/* change for domain model */));
//    }
//}

@Component
@AllArgsConstructor
public class JPARepositoryAdapter implements FlightsGateway {

    @Override
    public FlightEntity getFlights(FlightSearchInput input) {
        return FlightEntity.builder()
                .fxRate(35000F)
                .searchId("12")
                .currency("20000")
                .data(Collections.singletonList(FlightDetailsEntity.builder()
                                .id("1")
                                .aTime(555L)
                                .aTimeFormatted("HH")
                                .aTimeUTC(6665L)
                                .baggage(FlightBagPriceEntity.builder()
                                        .bagOnePrice(66500.00)
                                        .bagTwoPrice(56250.00)
                                        .build())
                                .cityCodeFrom("665")
                                .cityFrom("Colombia")
                                .dTime(65L)
                                .cityCodeTo("Mexico")
                                .dTimeFormatted("HH")
                                .flyFrom("Colombia")
                                .dTimeUTC(564L)
                                .price(369452F)
                                .cityTo("Medellin")
                                .flyTo("Venezuela")

                        .build()))
                .build();
    }

    @Override
    public LocationEntity getLocation(String iata) {
        return LocationEntity.builder()
                .locations(Collections.singletonList(FlightLocationEntity.builder()
                                .code("55")
                        .build()))
                .build();
    }
}