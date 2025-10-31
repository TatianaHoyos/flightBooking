package co.com.thoyos.api.dto;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class FlightResponseDto {
   private Map<String, FlightResumeDetailsDto> averageFlights;
   private String dateFrom;
   private String dateTo;
}
