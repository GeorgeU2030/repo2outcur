package co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.util;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.LoginInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.LoginOutDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class BaseSmokeIT {

    public static final String OUTCURRAPI_V_1_USERS_LOGIN = "/outcurrapi/v1/auth/users/login";

    public String getTestUserJWTToken(String username, String password, String server) {
        LoginInDTO loginDTO = new LoginInDTO(username, password);

        RestTemplate testTemplate = new RestTemplate();

        LoginOutDTO authenticationResponse = testTemplate.postForObject(server + OUTCURRAPI_V_1_USERS_LOGIN, loginDTO,
                LoginOutDTO.class);

        return authenticationResponse.accessToken();
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}