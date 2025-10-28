package co.com.thoyos.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiRestTest {

    FlightRestController flightRest = new FlightRestController();

    @Test
    void apiRestTest() {
        var response = flightRest.commandName();
        assertEquals("", response);
    }
}
