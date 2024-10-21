package co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.banner;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyNamesRequestDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.banner.BannerFacultyImportController;
import co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.util.BaseSmokeIT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {BannerFacultyImportController.class})
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BannerFacultyImportControllerSmokeIT extends BaseSmokeIT {

    public static final String OUT_CURR_TEST_USER = "OutCurrTestUser";
    public static final String USER_PASSWORD = "123456";
    private static String testUserJWTToken;
    public static final String V_1_AUTH_IMPORT_FACULTIES = "/outcurrapi/v1/external/banner/faculties/";
    public static final String OUTCURRAPI_V_1_AUTH_FACULTIES = "/outcurrapi/v1/auth/faculties/";

    @Value("${test.server.url}")
    private String server;

    @BeforeAll
    void init() {
        testUserJWTToken = getTestUserJWTToken(OUT_CURR_TEST_USER, USER_PASSWORD, server);
    }

    @Test
    void getFacultiesList() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpEntity<String> jwtEntity = new HttpEntity<>(getRequestHeaders());

        String url = server + V_1_AUTH_IMPORT_FACULTIES;
        ResponseEntity<List<FacultyOutDTO>> response = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                jwtEntity,
                new ParameterizedTypeReference<>(){});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getFacultiesPage() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpEntity<String> jwtEntity = new HttpEntity<>(getRequestHeaders());

        String url = server + V_1_AUTH_IMPORT_FACULTIES + "page?page=1&size=10";
        ResponseEntity<String> response = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                jwtEntity,
                new ParameterizedTypeReference<>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void importFaculties() throws JsonProcessingException {
        TestRestTemplate testRestTemplate = new TestRestTemplate();

        List<String> facultyNames = List.of("Administrative and Economic Sciences", "Ciencias de la Salud");
        FacultyNamesRequestDTO requestBody = new FacultyNamesRequestDTO(facultyNames);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBodyJson = objectMapper.writeValueAsString(requestBody);

        HttpEntity<String> jwtEntity = new HttpEntity<>(requestBodyJson, getRequestHeaders());

        String url = server + V_1_AUTH_IMPORT_FACULTIES;
        ResponseEntity<List<FacultyOutDTO>> response = testRestTemplate.exchange(
                url,
                HttpMethod.POST,
                jwtEntity,
                new ParameterizedTypeReference<>(){});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        deleteFaculty(response.getBody().get(0).facId());
        deleteFaculty(response.getBody().get(1).facId());
    }

    private HttpHeaders getRequestHeaders() {
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);
        return headers;
    }

    private void deleteFaculty(Long facId){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + "/";
        ResponseEntity<Void> response = testRestTemplate.exchange(
                url+facId,
                HttpMethod.DELETE,
                jwtEntity,
                Void.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    // new test
    @Test
    void getFacultiesListUnauthorized() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = getHeaders(); // without token JWT
        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);

        String url = server + V_1_AUTH_IMPORT_FACULTIES;
        ResponseEntity<String> response = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                jwtEntity,
                String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    // new test
    @Test
    void getFacultyByInvalidId() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpEntity<String> jwtEntity = new HttpEntity<>(getRequestHeaders());

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + "/999999"; 
        ResponseEntity<String> response = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                jwtEntity,
                String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // new test
    @Test
    void deleteFacultyWithoutPermissions() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        
        // The current user does not have permissions to delete a faculty
        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + "/1"; 
        HttpHeaders headers = getRequestHeaders();
        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = testRestTemplate.exchange(
                url,
                HttpMethod.DELETE,
                jwtEntity,
                Void.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    
}
