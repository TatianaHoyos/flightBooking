package co.com.thoyos.api.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataFlight {
    private Map<String, FlightResumeDetailsDto> averageFlights;
    private String dateFrom;
    private String dateTo;
}
