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
public class JPARepositoryAdapter {

}