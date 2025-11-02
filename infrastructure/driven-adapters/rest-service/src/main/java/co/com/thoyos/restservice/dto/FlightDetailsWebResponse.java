package co.com.thoyos.restservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
public class FlightDetailsWebResponse {
   private String id;
    private String flyFrom;
    private String cityFrom;
    private String cityCodeFrom;
    private String flyTo;
    private String cityTo;
    private String cityCodeTo;
    private Float price;
    @JsonProperty("dTime")
    private Long dTime;
    @JsonProperty("dTimeFormatted")
    private String dTimeFormatted;
    @JsonProperty("aTimeFormatted")
    private String aTimeFormatted;
    @JsonProperty("dTimeUTC")
    private Long dTimeUTC;
    @JsonProperty("aTime")
    private Long aTime;
    @JsonProperty("aTimeUTC")
    private Long aTimeUTC;
    @JsonProperty("bags_price")
    private FlightBagPriceWebResponse baggage;
}
