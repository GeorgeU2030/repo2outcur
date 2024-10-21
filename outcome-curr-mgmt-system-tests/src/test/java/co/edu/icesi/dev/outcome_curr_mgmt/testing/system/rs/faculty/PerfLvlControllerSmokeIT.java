package co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.PerfLvlInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.PerfLvlOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthPerfLvlController;
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

import static co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.faculty.FacultyControllerSmokeIT.OUTCURRAPI_V_1_AUTH_FACULTIES;
import static co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.faculty.FacultyControllerSmokeIT.OUT_CURR_TEST_USER;
import static co.edu.icesi.dev.outcome_curr_mgmt.testing.system.rs.faculty.FacultyControllerSmokeIT.USER_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = {AuthPerfLvlController.class})
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Timeout(15)
public class PerfLvlControllerSmokeIT  extends BaseSmokeIT {

    private static String testUserJWTToken;

    private static PerfLvlInDTO perfLvlInDTO;

    public static final char IS_ACTIVE='Y';

    public static final String PL_NAME_SPA = "Un nivel de desempeño";

    public static final String PL_NAME_ENG = "A performance level";

    public static final String PL_NAME_SPA2 = "Otro nivel de desempeño";

    public static final String PL_NAME_ENG2 = "Another performance level";

    public static final int PL_ORDER = 10;


    @Value("${test.server.url}")
    private String server;


    @BeforeAll
    void init(){
        testUserJWTToken = getTestUserJWTToken(OUT_CURR_TEST_USER, USER_PASSWORD, server);
        perfLvlInDTO = PerfLvlInDTO.builder()
                .plIsActive(IS_ACTIVE)
                .plOrder(PL_ORDER)
                .plNameEng(PL_NAME_ENG)
                .plNameSpa(PL_NAME_SPA)
                .build();
    }

    @Test
    void testCreatePerfLvlHappyPath(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<PerfLvlInDTO> jwtEntity = new HttpEntity<>(perfLvlInDTO, headers);
        ResponseEntity<PerfLvlOutDTO> response = createPerfLvl(testRestTemplate,jwtEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(PL_ORDER, response.getBody().plOrder());
        assertEquals(PL_NAME_ENG, response.getBody().plNameEng());
        assertEquals(PL_NAME_SPA, response.getBody().plNameSpa());
        assertEquals(IS_ACTIVE, response.getBody().plIsActive());

        deletePerfLvl(testRestTemplate, jwtEntity, response.getBody().plId());
    }

    @Test
    void testCreatePerfLvlFail(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<PerfLvlInDTO> jwtEntity = new HttpEntity<>(perfLvlInDTO, headers);
        ResponseEntity<PerfLvlOutDTO> response = createPerfLvl(testRestTemplate,jwtEntity);
        assertNotNull(response.getBody());
        ResponseEntity<PerfLvlOutDTO> responseDuplicate = createPerfLvl(testRestTemplate,jwtEntity);

        assertEquals(HttpStatus.CONFLICT, responseDuplicate.getStatusCode());
        assertNotNull(responseDuplicate.getBody());
        assertEquals(0, responseDuplicate.getBody().plOrder());
        assertNull( responseDuplicate.getBody().plNameEng());
        assertNull( responseDuplicate.getBody().plNameSpa());
        assertEquals(0,responseDuplicate.getBody().plIsActive());

        deletePerfLvl(testRestTemplate, jwtEntity, response.getBody().plId());

    }

    @Test
    void testGetPerfLvlHappyPath(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<PerfLvlInDTO> jwtEntity = new HttpEntity<>(perfLvlInDTO, headers);
        ResponseEntity<PerfLvlOutDTO> response = createPerfLvl(testRestTemplate,jwtEntity);
        assertNotNull(response.getBody());

        HttpEntity<PerfLvlInDTO> jwtEntityGet = new HttpEntity<>(headers);
        ResponseEntity<PerfLvlOutDTO> responseGet = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_FACULTIES +"1/acad_programs/1/performance_levels/"+response.getBody().plId(),
                HttpMethod.GET,
                jwtEntityGet,
                new ParameterizedTypeReference<>() {
                });
        
        assertNotNull(responseGet.getBody());
        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        assertEquals(response.getBody().plId(),responseGet.getBody().plId());
        assertEquals( response.getBody().plOrder(),responseGet.getBody().plOrder());
        assertEquals(response.getBody().plNameEng(),responseGet.getBody().plNameEng());
        assertEquals( response.getBody().plNameSpa(),responseGet.getBody().plNameSpa());
        assertEquals( response.getBody().plIsActive(),responseGet.getBody().plIsActive());

        deletePerfLvl(testRestTemplate, jwtEntity, response.getBody().plId());
    }

    @Test
    void testGetPerfLvlFail(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<PerfLvlInDTO> jwtEntity = new HttpEntity<>(headers);

        ResponseEntity<PerfLvlOutDTO> responseGet = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_FACULTIES +"1/acad_programs/1/performance_levels/7000",
                HttpMethod.GET,
                jwtEntity,
                new ParameterizedTypeReference<>() {
                });

        assertNotNull(responseGet.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseGet.getStatusCode());

        assertEquals(0, responseGet.getBody().plOrder());
        assertNull( responseGet.getBody().plNameEng());
        assertNull( responseGet.getBody().plNameSpa());
        assertEquals(0,responseGet.getBody().plIsActive());


    }

    @Test
    void testGetAllPerfLvlsHappyPath(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<PerfLvlInDTO> jwtEntity = new HttpEntity<>(perfLvlInDTO, headers);
        ResponseEntity<PerfLvlOutDTO> response = createPerfLvl(testRestTemplate,jwtEntity);
        assertNotNull(response.getBody());

        HttpEntity<PerfLvlInDTO> jwtEntityGet = new HttpEntity<>(headers);
        ResponseEntity<List<PerfLvlOutDTO>> responseGet = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_FACULTIES +"1/acad_programs/1/performance_levels",
                HttpMethod.GET,
                jwtEntityGet,
                new ParameterizedTypeReference<>() {
                });

        assertNotNull(responseGet.getBody());
        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        assertEquals(1,responseGet.getBody().size());
        assertEquals(response.getBody().plId(),responseGet.getBody().get(0).plId());
        assertEquals( response.getBody().plOrder(),responseGet.getBody().get(0).plOrder());
        assertEquals(response.getBody().plNameEng(),responseGet.getBody().get(0).plNameEng());
        assertEquals( response.getBody().plNameSpa(),responseGet.getBody().get(0).plNameSpa());
        assertEquals( response.getBody().plIsActive(),responseGet.getBody().get(0).plIsActive());

        deletePerfLvl(testRestTemplate, jwtEntity, response.getBody().plId());
    }

    @Test
    void testUpdatePerfLvlHappyPath(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<PerfLvlInDTO> jwtEntity = new HttpEntity<>(perfLvlInDTO, headers);
        ResponseEntity<PerfLvlOutDTO> response =createPerfLvl(testRestTemplate,jwtEntity);
        assertNotNull(response.getBody());
        
        PerfLvlInDTO perfLvlUpdated=PerfLvlInDTO.builder()
                .plOrder(PL_ORDER)
                .plNameSpa(PL_NAME_SPA2)
                .plNameEng(PL_NAME_ENG2)
                .plIsActive(IS_ACTIVE)
                .build();

        HttpEntity<PerfLvlInDTO> jwtEntityUpdate= new HttpEntity<>(perfLvlUpdated, headers);

        ResponseEntity<PerfLvlOutDTO> responsePut = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_FACULTIES +"1/acad_programs/1/performance_levels/"+response.getBody().plId(),
                HttpMethod.PUT,
                jwtEntityUpdate,
                PerfLvlOutDTO.class);

        assertNotNull(responsePut.getBody());
        assertEquals(HttpStatus.OK, responsePut.getStatusCode());
        assertEquals(response.getBody().plId(),responsePut.getBody().plId());
        assertEquals(PL_ORDER, responsePut.getBody().plOrder());
        assertEquals(PL_NAME_ENG2, responsePut.getBody().plNameEng());
        assertEquals(PL_NAME_SPA2, responsePut.getBody().plNameSpa());
        assertEquals(IS_ACTIVE, responsePut.getBody().plIsActive());

        deletePerfLvl(testRestTemplate, jwtEntityUpdate, responsePut.getBody().plId());
    }

    @Test
    void testUpdatePerfLvlFail(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String token = "Bearer "+testUserJWTToken;
        HttpHeaders headers = getHeaders();
        headers.set("Authorization", token);

        HttpEntity<PerfLvlInDTO> jwtEntity = new HttpEntity<>(perfLvlInDTO, headers);
        ResponseEntity<PerfLvlOutDTO> response = createPerfLvl(testRestTemplate,jwtEntity);
        assertNotNull(response.getBody());

        PerfLvlInDTO perfLvlUpdated=PerfLvlInDTO.builder()
                .plOrder(PL_ORDER)
                .plNameSpa(PL_NAME_SPA2)
                .plNameEng(PL_NAME_ENG2)
                .plIsActive(IS_ACTIVE)
                .build();

        HttpEntity<PerfLvlInDTO> jwtEntity2 = new HttpEntity<>(perfLvlUpdated, headers);
        ResponseEntity<PerfLvlOutDTO> response2 =createPerfLvl(testRestTemplate,jwtEntity2);
        assertNotNull(response2.getBody());
        
        
        HttpEntity<PerfLvlInDTO> jwtEntityUpdate= new HttpEntity<>(perfLvlUpdated, headers);

        ResponseEntity<PerfLvlOutDTO> responsePut = testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_FACULTIES +"1/acad_programs/1/performance_levels/"+response.getBody().plId(),
                HttpMethod.PUT,
                jwtEntityUpdate,
                PerfLvlOutDTO.class);

        assertNotNull(responsePut.getBody());
        assertEquals(HttpStatus.CONFLICT, responsePut.getStatusCode());
        assertEquals(0, responsePut.getBody().plOrder());
        assertNull( responsePut.getBody().plNameEng());
        assertNull( responsePut.getBody().plNameSpa());
        assertEquals(0,responsePut.getBody().plIsActive());

        deletePerfLvl(testRestTemplate, jwtEntity, response.getBody().plId());
        deletePerfLvl(testRestTemplate, jwtEntity2, response2.getBody().plId());

    }

    private  ResponseEntity<PerfLvlOutDTO> createPerfLvl(TestRestTemplate testRestTemplate, HttpEntity<PerfLvlInDTO> perfLvl ){
        return testRestTemplate.postForEntity(
                server + OUTCURRAPI_V_1_AUTH_FACULTIES +"1/acad_programs/1/performance_levels",
                perfLvl,
                PerfLvlOutDTO.class);
    }

    private void deletePerfLvl(TestRestTemplate testRestTemplate,
            HttpEntity<PerfLvlInDTO> responseOfPerfLvl, long perfLvlId){

        testRestTemplate.exchange(
                server + OUTCURRAPI_V_1_AUTH_FACULTIES + "1/acad_programs/1/performance_levels/" + perfLvlId,
                HttpMethod.DELETE,
                responseOfPerfLvl, new ParameterizedTypeReference<String>() {
                });
    }

}
