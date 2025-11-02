package co.com.thoyos.restservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.NON_EMPTY)
public class FlightWebResponse {
    @JsonProperty("search_id")
    private String searchId;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("fx_rate")
    private Float fxRate;
    @JsonProperty("data")
    private List<FlightDetailsWebResponse> data;
}
