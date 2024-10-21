package co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthFacultyController;
import co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.util.BaseSmokeIT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = {AuthFacultyController.class})
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Timeout(15)
public class FacultyControllerSmokeIT extends BaseSmokeIT {

    public static final String OUT_CURR_TEST_USER = "OutCurrTestUser";
    public static final String USER_PASSWORD = "123456";
    private static String testUserJWTToken;
    private static FacultyInDTO newFaculty3;
    private static FacultyOutDTO facultyOutDTO1;
    private static FacultyOutDTO facultyOutDTO2;
    private static FacultyOutDTO facultyOutDTO3;
    private static final String FACULTY_NAME_SPA_1="Facultad1";
    private static final String FACULTY_NAME_ENG_1="Faculty1";
    private static final String FACULTY_NAME_SPA_2="Facultad2";
    private static final String FACULTY_NAME_ENG_2="Faculty2";
    private static final String FACULTY_NAME_SPA_3="Facultad3";
    private static final String FACULTY_NAME_ENG_3="Faculty3";
    private static final String ACTIVE="Y";
    private static final String NOT_ACTIVE="N";
    private static long newFacultyId = 0L;
    public static final String OUTCURRAPI_V_1_AUTH_FACULTIES = "/outcurrapi/v1/auth/faculties/";

    @Value("${test.server.url}")
    private String server;

    @BeforeAll
    void init() {
        testUserJWTToken = getTestUserJWTToken(OUT_CURR_TEST_USER, USER_PASSWORD, server);

        FacultyInDTO newFaculty1 = FacultyInDTO.builder()
                .isActive(ACTIVE)
                .facNameEng(FACULTY_NAME_ENG_1)
                .facNameSpa(FACULTY_NAME_SPA_1)
                .build();

        FacultyInDTO newFaculty2 = FacultyInDTO.builder()
                .isActive(ACTIVE)
                .facNameEng(FACULTY_NAME_ENG_2)
                .facNameSpa(FACULTY_NAME_SPA_2)
                .build();

        newFaculty3 = FacultyInDTO.builder()
                .isActive(ACTIVE)
                .facNameEng(FACULTY_NAME_ENG_3)
                .facNameSpa(FACULTY_NAME_SPA_3)
                .build();

        facultyOutDTO1 = postFaculty(server,testUserJWTToken, newFaculty1);
        facultyOutDTO2 = postFaculty(server,testUserJWTToken, newFaculty2);
        facultyOutDTO3 = postFaculty(server,testUserJWTToken, newFaculty3);
    }

    public FacultyOutDTO postFaculty(String server, String testUserJWTToken, FacultyInDTO newFaculty){
        RestTemplate testTemplate = new RestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<FacultyInDTO> requestEntity = new HttpEntity<>(newFaculty, headers);
        ResponseEntity<FacultyOutDTO> response = testTemplate.exchange(server + OUTCURRAPI_V_1_AUTH_FACULTIES, HttpMethod.POST,
                requestEntity, new ParameterizedTypeReference<FacultyOutDTO>(){});
        return response.getBody();
    }

    @Test
    void createFacultyHappyPath() {
        FacultyInDTO newFaculty = FacultyInDTO.builder()
                .isActive(ACTIVE)
                .facNameEng("Faculty4")
                .facNameSpa("Facultad4")
                .build();

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<FacultyInDTO> requestEntity = new HttpEntity<>(newFaculty, headers);
        ResponseEntity<FacultyOutDTO> response = testRestTemplate.exchange(server + OUTCURRAPI_V_1_AUTH_FACULTIES, HttpMethod.POST,
                requestEntity, new ParameterizedTypeReference<FacultyOutDTO>(){});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        newFacultyId = response.getBody().facId();
    }

    @Test
    void createFacultyFail() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<FacultyInDTO> requestEntity = new HttpEntity<>(newFaculty3, headers);
        ResponseEntity<FacultyOutDTO> response = testRestTemplate.exchange(server + OUTCURRAPI_V_1_AUTH_FACULTIES, HttpMethod.POST,
                requestEntity, new ParameterizedTypeReference<FacultyOutDTO>(){});
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getFacultyByFacIdHappyPath() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + facultyOutDTO1.facId();

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<FacultyOutDTO> response = testRestTemplate.exchange(url, HttpMethod.GET,
                jwtEntity, new ParameterizedTypeReference<FacultyOutDTO>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getFacultyByFacIdFail() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + "/10000";

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<FacultyOutDTO> response = testRestTemplate.exchange(url, HttpMethod.GET,
                jwtEntity, new ParameterizedTypeReference<FacultyOutDTO>() {});
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getFacultyByFacNameInSpa() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + "nameInSpa/" + facultyOutDTO3.facNameSpa();

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<FacultyOutDTO> response = testRestTemplate.exchange(url, HttpMethod.GET,
                jwtEntity, new ParameterizedTypeReference<FacultyOutDTO>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getFacultyByFacNameInEng() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + "nameInEng/" + facultyOutDTO3.facNameEng();

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<FacultyOutDTO> response = testRestTemplate.exchange(url, HttpMethod.GET,
                jwtEntity, new ParameterizedTypeReference<FacultyOutDTO>() {});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getFaculties() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<List<FacultyOutDTO>> response = testRestTemplate.exchange(server + OUTCURRAPI_V_1_AUTH_FACULTIES, HttpMethod.GET,
                jwtEntity, new ParameterizedTypeReference<List<FacultyOutDTO>>(){});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateFacultyHappyPath(){
        FacultyInDTO facultyToUpdate = FacultyInDTO.builder()
                .isActive(NOT_ACTIVE)
                .facNameEng("NewNameForFaculty1")
                .facNameSpa("NuevoNombreParaFacultad1")
                .build();

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + facultyOutDTO1.facId();

        HttpEntity<FacultyInDTO> requestEntity = new HttpEntity<>(facultyToUpdate, headers);
        ResponseEntity<FacultyOutDTO> response = testRestTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, new ParameterizedTypeReference<FacultyOutDTO>(){});
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateFacultyWithDuplicatedNameWillFail(){
        FacultyInDTO facultyToUpdate = FacultyInDTO.builder()
                .isActive(NOT_ACTIVE)
                .facNameEng(FACULTY_NAME_ENG_3)
                .facNameSpa(FACULTY_NAME_SPA_1)
                .build();

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + facultyOutDTO1.facId();

        HttpEntity<FacultyInDTO> requestEntity = new HttpEntity<>(facultyToUpdate, headers);
        ResponseEntity<FacultyOutDTO> response = testRestTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, new ParameterizedTypeReference<FacultyOutDTO>(){});
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void updateFacultyThatDoesNotExistWillFail(){
        FacultyInDTO facultyToUpdate = FacultyInDTO.builder()
                .isActive(NOT_ACTIVE)
                .facNameEng("Faculty8")
                .facNameSpa("Facultad8")
                .build();

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + "/10000";

        HttpEntity<FacultyInDTO> requestEntity = new HttpEntity<>(facultyToUpdate, headers);
        ResponseEntity<FacultyOutDTO> response = testRestTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, new ParameterizedTypeReference<FacultyOutDTO>(){});
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteFacultyHappyPath(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + facultyOutDTO2.facId();

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<Void> response = testRestTemplate.exchange(url, HttpMethod.DELETE, jwtEntity, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteFacultyThatDoesNotExistWillFail(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES + "/10000";

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<Void> response = testRestTemplate.exchange(url, HttpMethod.DELETE, jwtEntity, Void.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @AfterAll
    void cleanUp(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        String url = server + OUTCURRAPI_V_1_AUTH_FACULTIES;

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        testRestTemplate.exchange(url+facultyOutDTO1.facId(), HttpMethod.DELETE, jwtEntity, Void.class);
        testRestTemplate.exchange(url+newFacultyId, HttpMethod.DELETE, jwtEntity, Void.class);
        testRestTemplate.exchange(url+facultyOutDTO3.facId(), HttpMethod.DELETE, jwtEntity, Void.class);
    }
}