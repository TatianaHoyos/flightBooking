package co.com.thoyos.api;
import co.com.thoyos.api.builder.ResponseBuilder;
import co.com.thoyos.model.flight.dto.FlightResponseDto;
import co.com.thoyos.model.flight.dto.FlightSearchInput;
import co.com.thoyos.usecase.flight.FlightUseCase;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * API Rest controller.
 * 
 * Example of how to declare and use a use case:
 * <pre>
 * private final MyUseCase useCase;
 * 
 * public String commandName() {
 *     return useCase.execute();
 * }
 * </pre>
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class FlightRestController {

    private final FlightUseCase flightUseCase;
    private final ResponseBuilder responseDefault;
    private long timeStamp;

    @RequestMapping(value = "flights/avg", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<?> filterFlights(@PathParam("flyFrom")final String flyFrom,
                                           @PathParam("flyTo")final  String flyTo,
                                           @PathParam("currency")final String currency,
                                           @PathParam("dateFrom") final  String dateFrom,
                                           @PathParam("dateTo") final  String dateTo,
                                           @PathParam("airLines") final String airLines
                                           ){
        timeStamp = new Date().getTime();
        FlightSearchInput input = new FlightSearchInput(currency, dateFrom, dateTo,
                flyTo, flyFrom, airLines);
        FlightResponseDto filter = flightUseCase.filterFlights(input);

        return responseDefault.build(filter,timeStamp, HttpStatus.OK);
    }

    @GetMapping(path = "/usecase/path")
    public String commandName() {
        return "";
    }
}
