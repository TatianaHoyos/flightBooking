package co.com.thoyos.model.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FlightSearchInput {
   private String currency;
    private String dateFrom;
    private String dateTo;
    private String flyTo;
    private String flyFrom;
    private String airLines;
}
