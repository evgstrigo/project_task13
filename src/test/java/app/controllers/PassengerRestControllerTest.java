package app.controllers;

import app.entities.Passenger;
import app.repositories.PassengerRepository;
import app.util.PassengerAndPassportCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.util.NestedServletException;


import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for passenger REST controller<br>
 * At this moment I don't know exactly which kind of tests should present
 */

//@SpringBootTest
//@AutoConfigureMockMvc
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        DbUnitTestExecutionListener.class })
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})

@DatabaseSetup(value = "PassengerRestControllerDataSet.xml")
@DatabaseTearDown(value = "PassengerRestControllerDataSet.xml")
class PassengerRestControllerTest {

    /**
     * URL to controller for passenger entity
     */
    final static String URI_TEMPLATE = "/api/passengers";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PassengerRepository passengerRepository;

    /**
     * Trying to get passenger with incorrect id
     */
    @Test
    void getPassengerWithIncorrectId() throws Exception {
        assertThrows(NoSuchElementException.class, () -> {
            try {
                mockMvc.perform(get(URI_TEMPLATE + "/-1"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.email").value("qwe@mail.ru"));
            } catch (NestedServletException nse) {
                throw nse.getCause();
            }
        });
    }


    /**
     * Trying to update passenger(presented in DB)
     */
    @Test
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
            value = "PassengerRestControllerDataSetExpected.xml")
    void putOnePassenger() throws Exception {
        Passenger passenger = passengerRepository.findByEmail("mno@mail.ru");
        passenger.setEmail("xyz@mail.ru");
        mockMvc.perform(put(URI_TEMPLATE)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(passenger)));
    }


    /**
     * Trying to update passenger(presented in DB) with incorrect email format
     */
    @Test
    void putOnePassengerWithIncorrectEmail() throws Exception {
        assertThrows(TransactionSystemException.class, () -> {
            try {
                Passenger passenger = passengerRepository.findByEmail("mno@mail.ru");
                passenger.setEmail("xyz.ru");
                mockMvc.perform(put(URI_TEMPLATE)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(passenger)));
            } catch (NestedServletException nse) {
                throw nse.getCause();
            }
        });
    }


    /**
     * Trying to get all passengers from DB (count should be 5)
     */
    @Test
    void getAllPassengersShouldBeFive() throws Exception {
        mockMvc.perform(get(URI_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5));
    }


    /**
     * Trying to add 1 passenger to DB and check result
     *
     * @throws Exception
     */
    @Test
    void postOnePassenger() throws Exception {

        Passenger passenger = new Passenger();
        passenger.setEmail("passenger@mail.ru");

        mockMvc.perform(post(URI_TEMPLATE)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(passenger)))
                .andExpect(status().isOk());
        assertThat(passengerRepository.findByEmail("passenger@mail.ru") != null);
    }

}