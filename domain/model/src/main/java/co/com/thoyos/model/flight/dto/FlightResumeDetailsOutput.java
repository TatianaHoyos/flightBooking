package co.com.thoyos.model.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FlightResumeDetailsOutput {
    private String currency;
    private String cityName;
    private double priceAverage;
    private FlightBagPriceAverageDto bagsPrice;

    @Data
    @Builder
    @AllArgsConstructor
    public static class FlightBagPriceAverageDto {
        private double bagOneAveragePrice;
        private double bagTwoAveragePrice;

    }
}


