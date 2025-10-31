package co.com.thoyos.model.flight;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class FlightEntity {
    private String searchId;
    private String currency;
    private float fxRate;
    private List<FlightDetailsEntity> data;
}
