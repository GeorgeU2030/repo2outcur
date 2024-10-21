package co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.AcadPeriodInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.AcadPeriodOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.management.AuthAcademicPeriodController;
import co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.util.BaseSmokeIT;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = {AuthAcademicPeriodController.class})
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Timeout(15)
public class AcademicPeriodControllerSmokeIT extends BaseSmokeIT {

    public static final String OUT_CURR_TEST_USER = "OutCurrTestUser";

    public static final String USER_PASSWORD = "123456";

    private static String testUserJWTToken;

    private static AcadPeriodInDTO acadPeriodInDTO;

    public static final String OUTCURRAPI_V_1_AUTH_ACAD_PERIODS = "/outcurrapi/v1/auth/acad_periods";

    public static final String AC_PERIOD_NAME_SPA = "Un periodo academico";

    public static final String AC_PERIOD_NAME_ENG = "A academic period";

    public static final int AC_PERIOD_NUMERIC = 200000;

    public static final String AC_PERIOD_NAME_SPA2 = "Otro periodo academico";

    public static final String AC_PERIOD_NAME_ENG2 = "Another academic period";

    public static final int AC_PERIOD_NUMERIC2 = 200001;

    @Value("${test.server.url}")
    private String server;

    @BeforeAll
    void init(){
        testUserJWTToken = getTestUserJWTToken(OUT_CURR_TEST_USER, USER_PASSWORD, server);
        acadPeriodInDTO = AcadPeriodInDTO.builder()
                .acPeriodNumeric(AC_PERIOD_NUMERIC)
                .acPeriodNameSpa(AC_PERIOD_NAME_SPA)
                .acPeriodNameEng(AC_PERIOD_NAME_ENG)
                .build();
    }

    @Test
    void testCreateAcademicPeriodHappyPath(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<AcadPeriodInDTO> jwtEntity = new HttpEntity<>(acadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> response = testRestTemplate.postForEntity(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS +"/",
                jwtEntity,
                AcadPeriodOutDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(AC_PERIOD_NUMERIC, response.getBody().acPeriodNumeric());
        assertEquals(AC_PERIOD_NAME_SPA, response.getBody().acPeriodNameSpa());

        deleteAcademicPeriod(testRestTemplate, jwtEntity, response.getBody().acPeriodId());
    }

    @Test
    void testCreateAcademicPeriodFail(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<AcadPeriodInDTO> createAcademicPeriod = new HttpEntity<>(acadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> responseCreateAcademicPeriod = testRestTemplate.postForEntity(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS +"/",
                createAcademicPeriod,
                AcadPeriodOutDTO.class);

        assertNotNull(responseCreateAcademicPeriod.getBody());

        HttpEntity<AcadPeriodInDTO> jwtEntity = new HttpEntity<>(acadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> response = testRestTemplate.postForEntity(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS +"/",
                jwtEntity,
                AcadPeriodOutDTO.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNull(response.getBody().acPeriodId());
        assertEquals(0, response.getBody().acPeriodNumeric());
        assertNull(response.getBody().acPeriodNameSpa());

        deleteAcademicPeriod(testRestTemplate, createAcademicPeriod, responseCreateAcademicPeriod.getBody().acPeriodId());
    }

    @Test
    void testGetAcademicPeriod(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<AcadPeriodInDTO> createAcademicPeriod = new HttpEntity<>(acadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfCreateAcPeriod = createAcademicPeriod(testRestTemplate, createAcademicPeriod);
        assertNotNull(responseOfCreateAcPeriod.getBody());

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfGetAcPeriod = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS + "/" +responseOfCreateAcPeriod.getBody().acPeriodId(),
                HttpMethod.GET,
                jwtEntity, new ParameterizedTypeReference<AcadPeriodOutDTO>(){});

        assertNotNull(responseOfGetAcPeriod.getBody());
        assertEquals(HttpStatus.OK, responseOfGetAcPeriod.getStatusCode());
        assertEquals(responseOfCreateAcPeriod.getBody().acPeriodId(), responseOfGetAcPeriod.getBody().acPeriodId());
        assertEquals(AC_PERIOD_NUMERIC, responseOfGetAcPeriod.getBody().acPeriodNumeric());
        assertEquals(AC_PERIOD_NAME_SPA, responseOfGetAcPeriod.getBody().acPeriodNameSpa());
        assertEquals(AC_PERIOD_NAME_ENG, responseOfGetAcPeriod.getBody().acPeriodNameEng());

        deleteAcademicPeriod(testRestTemplate, createAcademicPeriod, responseOfCreateAcPeriod.getBody().acPeriodId());
    }

    @Test
    void testGetAcademicPeriodFail(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<AcadPeriodInDTO> createAcademicPeriod = new HttpEntity<>(acadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfCreateAcPeriod = createAcademicPeriod(testRestTemplate, createAcademicPeriod);
        assertNotNull(responseOfCreateAcPeriod.getBody());

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfGetAcPeriod = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS + "/10000",
                HttpMethod.GET,
                jwtEntity, new ParameterizedTypeReference<AcadPeriodOutDTO>(){});

        assertNotNull(responseOfGetAcPeriod.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseOfGetAcPeriod.getStatusCode());
        assertNull(responseOfGetAcPeriod.getBody().acPeriodId());
        assertEquals(0, responseOfGetAcPeriod.getBody().acPeriodNumeric());
        assertNull(responseOfGetAcPeriod.getBody().acPeriodNameSpa());
        assertNull(responseOfGetAcPeriod.getBody().acPeriodNameEng());

        deleteAcademicPeriod(testRestTemplate, createAcademicPeriod, responseOfCreateAcPeriod.getBody().acPeriodId());
    }

    @Test
    void testGetAcademicPeriods() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
        ResponseEntity<List<AcadPeriodOutDTO>> response = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS,
                HttpMethod.GET,
                jwtEntity, new ParameterizedTypeReference<List<AcadPeriodOutDTO>>(){});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testSetAcademicPeriod(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<AcadPeriodInDTO> createAcademicPeriod = new HttpEntity<>(acadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfCreateAcPeriod = createAcademicPeriod(testRestTemplate, createAcademicPeriod);
        assertNotNull(responseOfCreateAcPeriod.getBody());

        AcadPeriodInDTO acPeriodUpdated = AcadPeriodInDTO.builder()
                .acPeriodNumeric(AC_PERIOD_NUMERIC2)
                .acPeriodNameSpa(AC_PERIOD_NAME_SPA2)
                .acPeriodNameEng(AC_PERIOD_NAME_ENG2)
                .build();

        HttpEntity<AcadPeriodInDTO> setAcademicPeriod = new HttpEntity<>(acPeriodUpdated, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfSetAcPeriod = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS +"/"+ responseOfCreateAcPeriod.getBody().acPeriodId(),
                HttpMethod.PUT,
                setAcademicPeriod,
                AcadPeriodOutDTO.class);

        assertNotNull(responseOfSetAcPeriod.getBody());
        assertEquals(HttpStatus.OK, responseOfSetAcPeriod.getStatusCode());
        assertEquals(responseOfCreateAcPeriod.getBody().acPeriodId(), responseOfSetAcPeriod.getBody().acPeriodId());
        assertEquals(AC_PERIOD_NUMERIC2, responseOfSetAcPeriod.getBody().acPeriodNumeric());
        assertEquals(AC_PERIOD_NAME_SPA2, responseOfSetAcPeriod.getBody().acPeriodNameSpa());
        assertEquals(AC_PERIOD_NAME_ENG2, responseOfSetAcPeriod.getBody().acPeriodNameEng());

        deleteAcademicPeriod(testRestTemplate, createAcademicPeriod, responseOfCreateAcPeriod.getBody().acPeriodId());
    }

    @Test
    void testSetAcademicPeriodFail(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<AcadPeriodInDTO> createAcademicPeriod = new HttpEntity<>(acadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfCreateAcPeriod = createAcademicPeriod(testRestTemplate, createAcademicPeriod);
        assertNotNull(responseOfCreateAcPeriod.getBody());

        AcadPeriodInDTO acPeriodToCreate2 = AcadPeriodInDTO.builder()
                .acPeriodNumeric(AC_PERIOD_NUMERIC2)
                .acPeriodNameSpa(AC_PERIOD_NAME_SPA2)
                .acPeriodNameEng(AC_PERIOD_NAME_ENG2)
                .build();

        HttpEntity<AcadPeriodInDTO> createAcademicPeriod2 = new HttpEntity<>(acPeriodToCreate2, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfCreateAcPeriod2 = createAcademicPeriod(testRestTemplate, createAcademicPeriod2);
        assertNotNull(responseOfCreateAcPeriod2.getBody());

        AcadPeriodInDTO acPeriodUpdated = AcadPeriodInDTO.builder()
                .acPeriodNumeric(AC_PERIOD_NUMERIC2)
                .acPeriodNameSpa(AC_PERIOD_NAME_SPA2)
                .acPeriodNameEng(AC_PERIOD_NAME_ENG2)
                .build();

        HttpEntity<AcadPeriodInDTO> setAcademicPeriod = new HttpEntity<>(acPeriodUpdated, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfSetAcPeriod = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS +"/"+ responseOfCreateAcPeriod.getBody().acPeriodId(),
                HttpMethod.PUT,
                setAcademicPeriod,
                AcadPeriodOutDTO.class);

        assertNotNull(responseOfSetAcPeriod.getBody());
        assertEquals(HttpStatus.CONFLICT, responseOfSetAcPeriod.getStatusCode());
        assertNull(responseOfSetAcPeriod.getBody().acPeriodId());
        assertEquals(0, responseOfSetAcPeriod.getBody().acPeriodNumeric());
        assertNull(responseOfSetAcPeriod.getBody().acPeriodNameSpa());
        assertNull(responseOfSetAcPeriod.getBody().acPeriodNameEng());

        deleteAcademicPeriod(testRestTemplate, createAcademicPeriod, responseOfCreateAcPeriod.getBody().acPeriodId());
        deleteAcademicPeriod(testRestTemplate, createAcademicPeriod2, responseOfCreateAcPeriod2.getBody().acPeriodId());
    }

    @Test
    void testDeleteAcademicPeriodHappyPath(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<AcadPeriodInDTO> createAcademicPeriod = new HttpEntity<>(acadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfCreateAcPeriod = createAcademicPeriod(testRestTemplate, createAcademicPeriod);
        assertNotNull(responseOfCreateAcPeriod.getBody());

        HttpEntity<Long> deleteAcademicPeriod = new HttpEntity<>(headers);
        ResponseEntity<Void> responseOfDeleteAcPeriod = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS +"/"+ responseOfCreateAcPeriod.getBody().acPeriodId(),
                HttpMethod.DELETE,
                deleteAcademicPeriod, Void.class);

        assertNull(responseOfDeleteAcPeriod.getBody());
        assertEquals(HttpStatus.NO_CONTENT, responseOfDeleteAcPeriod.getStatusCode());
    }

    private ResponseEntity<AcadPeriodOutDTO> createAcademicPeriod(TestRestTemplate testRestTemplate,
            HttpEntity<AcadPeriodInDTO> createAcademicPeriod){

        return testRestTemplate.postForEntity(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS +"/",
                createAcademicPeriod,
                AcadPeriodOutDTO.class);
    }

    private void deleteAcademicPeriod(TestRestTemplate testRestTemplate,
            HttpEntity<AcadPeriodInDTO> responseOfCreateAcPeriod, long deleteAcademicPeriod){

        testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS + "/" + deleteAcademicPeriod,
                HttpMethod.DELETE,
                responseOfCreateAcPeriod, new ParameterizedTypeReference<String>() {
                });
    }

    // new test
    @Test
    void testCreateAcademicPeriodMissingFields() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        AcadPeriodInDTO invalidAcadPeriodInDTO = AcadPeriodInDTO.builder()
                .acPeriodNumeric(0) // Falta el nombre
                .build();

        HttpEntity<AcadPeriodInDTO> jwtEntity = new HttpEntity<>(invalidAcadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> response = testRestTemplate.postForEntity(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS + "/",
                jwtEntity,
                AcadPeriodOutDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody().acPeriodId());
   }

   // new test
   @Test
   void testUpdateAcademicPeriodInvalidData() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer " + testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<AcadPeriodInDTO> createAcademicPeriod = new HttpEntity<>(acadPeriodInDTO, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfCreateAcPeriod = createAcademicPeriod(testRestTemplate, createAcademicPeriod);
        assertNotNull(responseOfCreateAcPeriod.getBody());

        AcadPeriodInDTO invalidUpdate = AcadPeriodInDTO.builder()
                .acPeriodNumeric(0)  // Dato inválido
                .acPeriodNameSpa("")  // Nombre vacío
                .build();

        HttpEntity<AcadPeriodInDTO> updateRequest = new HttpEntity<>(invalidUpdate, headers);
        ResponseEntity<AcadPeriodOutDTO> responseOfUpdate = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_ACAD_PERIODS + "/" + responseOfCreateAcPeriod.getBody().acPeriodId(),
                HttpMethod.PUT,
                updateRequest,
                AcadPeriodOutDTO.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseOfUpdate.getStatusCode());
        assertNotNull(responseOfUpdate.getBody());
   }


}
