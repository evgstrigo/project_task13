package app.controllers;

import app.entities.AbstractApplicationUser;
import app.entities.Admin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тест проверки работы Rest API Users
 */


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DbUnitConfiguration(databaseConnection = "dataSource")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "user.xml")
@DatabaseTearDown(value = "user.xml")
class AbstractUserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    final static String URL = "http://localhost:8888/api/users";

    @Test
    void getAllUsers() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getUserById() throws Exception {
        mockMvc.perform(get(URL + "/1"))
                .andExpect(jsonPath("$.age").value("35"))
                .andExpect(jsonPath("$.lastName").value("Lipin"))
                .andExpect(jsonPath("$.email").value("tyz_ft@list.ru"));
    }

    @Test
    void createUser() throws Exception {
        AbstractApplicationUser user = new Admin();
        user.setId(3L);
        user.setFirstName("Petr");
        user.setLastName("Petrov");
        user.setAge(75);
        user.setEmail("petrusha@gmail.com");
        user.setPassword("petrov");

        mockMvc.perform(post(URL).contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        AbstractApplicationUser abstractApplicationUser = new Admin();
        abstractApplicationUser.setId(1L);
        abstractApplicationUser.setFirstName("Vladimir");
        abstractApplicationUser.setLastName("Zhirinovsky");
        abstractApplicationUser.setAge(75);
        abstractApplicationUser.setEmail("zhirinovsky@gmail.com");
        abstractApplicationUser.setPassword("petrov");

        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(abstractApplicationUser)))
                .andExpect(status().isOk());

        mockMvc.perform(get(URL + "/1"))
                .andExpect(jsonPath("$.firstName").value("Vladimir"))
                .andExpect(jsonPath("$.lastName").value("Zhirinovsky"))
                .andExpect(jsonPath("$.email").value("zhirinovsky@gmail.com"));
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete(URL + "/1"))
                .andExpect(status().isOk());
    }
}
