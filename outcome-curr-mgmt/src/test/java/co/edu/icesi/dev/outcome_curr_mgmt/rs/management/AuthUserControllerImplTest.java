package co.edu.icesi.dev.outcome_curr_mgmt.rs.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.LoginInDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthUserControllerImplTest {
    //TODO this should be split into  system tests, and integration tests (without calling a real SAAMFI)
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void test_successful_login_with_valid_username_and_password() throws Exception {
        LoginInDTO loginDTO = new LoginInDTO("OutCurrTestUser", "123456");

        RequestBuilder request = post("/v1/auth/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginDTO))
                .accept(MediaType.APPLICATION_JSON);

       mvc.perform(request)
               .andExpect(status().isOk())
               .andReturn();

    }

    @Test
    void test_failed_login_with_invalid_username_and_password() throws Exception {
        LoginInDTO loginDTO = new LoginInDTO("OutCurrTestUser", "1234567");

        RequestBuilder request = post("/v1/auth/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginDTO))
                .accept(MediaType.APPLICATION_JSON);
        try {
            mvc.perform(request)
                    .andExpect(status().isForbidden())
                    .andReturn();
        } catch (OutCurrException e) {
            assertEquals("Incorrect credentials", e.getMessage());
        }
    }

}
