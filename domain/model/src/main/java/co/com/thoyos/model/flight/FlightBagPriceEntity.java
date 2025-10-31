package co.com.thoyos.model.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class FlightBagPriceEntity {
    private double bagOnePrice;
    private double bagTwoPrice;
}
