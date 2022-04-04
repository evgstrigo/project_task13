package app.controllers;

import app.entities.CountryCode;
import app.entities.Destination;
import app.repositories.DestinationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.TimeZone;

@SpringBootTest
@AutoConfigureMockMvc
class DestinationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DestinationRepository destinationRepository;

    final static String URI_TEMPLATE = "http://localhost:8888/api/destinations";

    @Test
    void addDestination() throws Exception {
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setSity("Moscow");
        destination.setCountryCode(CountryCode.RUS_643);
        destination.setCountry_name("Russia");
        destination.setAirport_name("Domodedovo");
        destination.setAirport_code("DME");
        destination.setTimezone(TimeZone.getTimeZone("Europe/Moscow"));

        mockMvc.perform(post(URI_TEMPLATE)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(destination)))
                .andExpect(status().isCreated());

        Destination destinationFromDb = destinationRepository.findBySity("Moscow");
        assertThat(destinationFromDb.getAirport_name()).isEqualTo("Domodedovo");

    }

    @Test
    void getDestinationById() throws Exception {
        mockMvc.perform(get(URI_TEMPLATE+"/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sity").value("Moscow"));
    }

    @Test
    void getDestinationBySity() throws Exception {
        mockMvc.perform(get(URI_TEMPLATE+"?sity=Moscow"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country_name").value("Russia"));
    }

    @Test
    void update() throws Exception {
        Destination destination = new Destination();
        destination.setSity("Moscow");
        destination.setCountryCode(CountryCode.BRA_076);
        destination.setCountry_name("Russia");
        destination.setAirport_name("Domodedovo");
        destination.setAirport_code("DME");
        destination.setTimezone(TimeZone.getTimeZone("Europe/Moscow"));

        mockMvc.perform(put(URI_TEMPLATE + "/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(destination)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryCode").value("BRA_076"));
    }
}