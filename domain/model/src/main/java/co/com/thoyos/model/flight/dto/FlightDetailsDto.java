package co.com.thoyos.model.flight.dto;

public class FlightDetailsDto {
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
    private  Long aTimeUTC;
    private FlightBagPriceDto baggage;
}
