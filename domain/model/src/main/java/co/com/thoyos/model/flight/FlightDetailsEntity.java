package co.com.thoyos.model.flight;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class FlightDetailsEntity {
    private String id;
    private String flyFrom;
    private String cityFrom;
    private String cityCodeFrom;
    private String flyTo;
    private String cityTo;
    private String cityCodeTo;
    private Float price;
    private Long dTime;
    private String dTimeFormatted;
    private String aTimeFormatted;
    private Long dTimeUTC;
    private Long aTime;
    private Long aTimeUTC;
    private FlightBagPriceEntity baggage;
}
