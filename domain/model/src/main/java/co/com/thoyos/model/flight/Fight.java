package co.com.thoyos.model.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Fight {
    private String searchId;
    private float fxRate;
    private List<FlightDetails> data;
}
