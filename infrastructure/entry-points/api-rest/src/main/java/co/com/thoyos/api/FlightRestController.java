package co.com.thoyos.api;
import co.com.thoyos.api.service.FlightService;
import co.com.thoyos.model.flight.dto.FlightSearchInput;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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

    private final FlightService service;

    @RequestMapping(value = "flights/avg", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<?> filterFlights(@RequestParam("flyFrom")final String flyFrom,
                                           @RequestParam("flyTo")final  String flyTo,
                                           @RequestParam("currency")final String currency,
                                           @RequestParam("dateFrom") final  String dateFrom,
                                           @RequestParam("dateTo") final  String dateTo,
                                           @RequestParam("airLines") final String airLines
                                           ) throws ParseException {

        FlightSearchInput input = new FlightSearchInput(currency, dateFrom, dateTo,
                flyTo, flyFrom, airLines);
        ResponseEntity<Object> flight = service.getFlight(input);
        return flight;
    }

    @GetMapping(path = "/usecase/path")
    public String commandName() {
        return "";
    }

    @PostMapping(path = "/usecase/path")
    public String commandName2() {
        return "";
    }
}
