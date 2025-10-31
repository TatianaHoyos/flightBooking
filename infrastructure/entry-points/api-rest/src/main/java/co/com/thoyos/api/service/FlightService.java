package co.com.thoyos.api.service;

import co.com.thoyos.api.builder.ResponseBuilder;
import co.com.thoyos.api.dto.FlightBagPriceAverageDto;
import co.com.thoyos.api.dto.FlightResponseDto;
import co.com.thoyos.api.dto.FlightResumeDetailsDto;
import co.com.thoyos.model.flight.dto.FlightResumeDetailsOutput;
import co.com.thoyos.model.flight.dto.FlightSearchInput;
import co.com.thoyos.model.flight.utils.DateTimeFormatterConfig;
import co.com.thoyos.usecase.flight.FlightUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class FlightService {
    private final FlightUseCase flightUseCase;
    private final ResponseBuilder responseDefault;

    public ResponseEntity<Object> getFlight(FlightSearchInput input) throws ParseException {
        Map<String, FlightResumeDetailsOutput> resume = flightUseCase.filterFlights(input);
        //TODO: mapper output to dto
        Map<String, FlightResumeDetailsDto> resumeDto = new HashMap<>();

        for(Map.Entry<String, FlightResumeDetailsOutput> output : resume.entrySet()){
            FlightResumeDetailsOutput resultFlight = output.getValue();

            FlightBagPriceAverageDto bagDto = new FlightBagPriceAverageDto(resultFlight.getBagsPrice().getBagOneAveragePrice(),
                    resultFlight.getBagsPrice().getBagTwoAveragePrice());

            FlightResumeDetailsDto dto = new FlightResumeDetailsDto(resultFlight.getCurrency(), resultFlight.getCityName(),
                    resultFlight.getPriceAverage(), bagDto);
            resumeDto.put(output.getKey(),dto);
        }

        FlightResponseDto response = new FlightResponseDto(resumeDto,
                DateTimeFormatterConfig.convertIsoFormat(input.getDateFrom()),
                DateTimeFormatterConfig.convertIsoFormat(input.getDateTo()));
        long timeStamp =  new Date().getTime();
        return responseDefault.build(response,timeStamp, HttpStatus.OK);
    }

}
