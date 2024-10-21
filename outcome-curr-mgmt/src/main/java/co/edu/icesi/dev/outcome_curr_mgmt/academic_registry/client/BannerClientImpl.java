package co.edu.icesi.dev.outcome_curr_mgmt.academic_registry.client;

import co.edu.icesi.dev.outcome_curr.mgmt.model.banner.auth.BannerCredentialsDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.banner.auth.BannerJwtDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.banner.data.BannerCourseDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.AcadProgramOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.academic_registry.config.BannerClientConfig;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.FacultyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class BannerClientImpl implements BannerAPI, BannerClient {
    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private final BannerClientConfig bannerClientConfig;
    private final RestTemplate restTemplate;
    private String authToken;
    private Instant tokenExpiration;

    @Autowired
    public BannerClientImpl(BannerClientConfig bannerClientConfig) {
        this.bannerClientConfig = bannerClientConfig;
        restTemplate = new RestTemplate();
    }

    @Override
    @Cacheable(value = "faculties", unless = "#result == null")
    public List<FacultyInDTO> getFacultiesList() {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(bannerClientConfig.getBannerUrl())
                .path("faculties");
        ResponseEntity<List<FacultyInDTO>> response = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                getAuthHeader(),
                new ParameterizedTypeReference<>() {}
        );
        System.out.println(response.getBody());
        return response.getBody();
    }

    @Override
    public Page<FacultyInDTO> getFacultiesPage(int page, int size) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(bannerClientConfig.getBannerUrl())
                .path("faculties")
                .queryParam("_limit", size)
                .queryParam("_page", page);
        ResponseEntity<List<FacultyInDTO>> listResponseEntity = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                getAuthHeader(),
                new ParameterizedTypeReference<>() {}
        );
        return formatPageResponse(page, size, listResponseEntity);
    }

    @Override
    @CacheEvict(value = "faculties")
    public List<FacultyInDTO> importFaculties(List<String> facultiesNames) {
        List<FacultyInDTO> foundFaculties = getFacultiesList().stream()
                .filter(faculty ->
                        facultiesNames.contains(faculty.facNameEng()) ||
                        facultiesNames.contains(faculty.facNameSpa())
                ).toList();
        if (foundFaculties.isEmpty()) {
            logger.error("The faculties dont match the given names");
            throw new OutCurrException(OutCurrExceptionType.FACULTY_NOT_FOUND);
        }
        return foundFaculties;
    }

    @Override
    @Cacheable(value = "programs", key = "'faculty-' + #facultyName", unless = "#result == null")
    public List<AcadProgramOutDTO> getAcadProgramsList(String facultyName) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(bannerClientConfig.getBannerUrl())
                .path("programs")
                .queryParam("faculty.facultyId", facultyName);
        ResponseEntity<List<AcadProgramOutDTO>> listResponseEntity = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                getAuthHeader(),
                new ParameterizedTypeReference<>() {}
        );
        return listResponseEntity.getBody();
    }

    @Override
    public Page<AcadProgramOutDTO> getAcadProgramsPage(int page, int size, String facultyName) {
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(bannerClientConfig.getBannerUrl())
                .path("programs")
                .queryParam("faculty.facultyId", facultyName)
                .queryParam("_limit", size)
                .queryParam("_page", page);
        ResponseEntity<List<AcadProgramOutDTO>> listResponseEntity = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                getAuthHeader(),
                new ParameterizedTypeReference<>() {}
        );
        return formatPageResponse(page, size, listResponseEntity);
    }

    @Override
    @CacheEvict(value = "programs", key = "'faculty-' + #facultyName")
    public List<AcadProgramOutDTO> importAcadPrograms(String facultyName, List<String> acadProgramsNames) {
        return getAcadProgramsList(facultyName).stream()
                .filter(faculty -> acadProgramsNames.contains(faculty.acpProgNameSpa())
                                || acadProgramsNames.contains(faculty.acpProgNameEng())
                ).toList();
    }

    @Override
    public String getAuthToken() {
        if (authToken == null || isAuthTokenExpired()) {
            return authToken = requestAuthToken();
        }
        return authToken;
    }

    private boolean isAuthTokenExpired() {
        return tokenExpiration != null && Instant.now().isAfter(tokenExpiration);
    }

    private String requestAuthToken() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BannerCredentialsDTO> request = new HttpEntity<>(
                    new BannerCredentialsDTO(
                            bannerClientConfig.getBannerUser(),
                            bannerClientConfig.getBannerPass()
                    ),
                    headers
            );
            ResponseEntity<BannerJwtDTO> responseEntity = restTemplate.postForEntity(
                    bannerClientConfig.getBannerUrl() + "login",
                    request,
                    BannerJwtDTO.class
            );
            tokenExpiration = Instant.now().plusSeconds(1440);
            return Objects.requireNonNull(responseEntity.getBody()).token();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HttpEntity<String> getAuthHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAuthToken());
        return new HttpEntity<>(headers);
    }

    private <T> Page<T> formatPageResponse(int page, int size, ResponseEntity<List<T>> bannerResponse) {
        return new PageImpl<>(
                Objects.requireNonNull(bannerResponse.getBody()),
                PageRequest.of(page, size),
                (long) size * getLastPage(bannerResponse)
        );
    }

    private <T> Integer getLastPage(ResponseEntity<List<T>> bannerResponse) {
        String linkHeader = bannerResponse.getHeaders().getFirst("Link");
        if (linkHeader != null) {
            String[] links = linkHeader.split(",");
            String last = Arrays.stream(links)
                    .filter(link -> link.contains("last")).findFirst().orElse(null);
            if (last != null) {
                String[] parts = last.split("page=");
                String[] partsSecond = parts[1].split(">");
                return Integer.parseInt(partsSecond[0]);
            }
            return 1;
        }
        return 1;
    }
}
