package co.edu.icesi.dev.outcome_curr_mgmt.service.banner;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.academic_registry.client.BannerAPI;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.FacultyMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty.FacultyProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.FacultyValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil.facultyInDTO;
import static co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil.facultyOutDTO;
import static co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil.secondFacultyInDTO;
import static co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil.secondFacultyOutDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BannerFacultyServiceTest {
    @Mock
    FacultyValidator facultyValidator;

    @Mock
    FacultyMapper facultyMapper;

    @Mock
    FacultyProvider facultyProvider;

    @Mock
    BannerAPI bannerAPI;

    @InjectMocks
    BannerFacultyServiceImpl bannerFacultyService;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(bannerFacultyService, "facultyValidator", facultyValidator);
        ReflectionTestUtils.setField(bannerFacultyService, "facultyMapper", facultyMapper);
        ReflectionTestUtils.setField(bannerFacultyService, "facultyProvider", facultyProvider);
        ReflectionTestUtils.setField(bannerFacultyService, "bannerAPI", bannerAPI);
    }

    @Test
    void Given_UserIsAuthenticated_When_GetFacultiesInListFromBanner_Then_ReturnFacultiesList() {
        FacultyInDTO firstFacultyToImport = facultyInDTO();
        FacultyInDTO secondFacultyToImport = secondFacultyInDTO();
        when(bannerAPI.getFacultiesList()).thenReturn(List.of(firstFacultyToImport, secondFacultyToImport));
        List<FacultyOutDTO> outDTOList = bannerFacultyService.getBannerFaculties();
        verify(bannerAPI, times(1)).getFacultiesList();
        assertEquals(2, outDTOList.size());
    }

    @Test
    void Given_UserIsAuthenticated_When_GetFacultiesInListFromBannerIsEmpty_Then_ThrowException() {
        when(bannerAPI.getFacultiesList()).thenReturn(List.of());
        OutCurrException exception = assertThrows(OutCurrException.class, () -> bannerFacultyService.getBannerFaculties());
        verify(bannerAPI, times(1)).getFacultiesList();
        assertEquals(
                OutCurrExceptionType.FACULTY_NOT_FOUND.getCode(),
                exception.getOutCurrExceptionType().getCode());
        assertEquals(
                OutCurrExceptionType.FACULTY_NOT_FOUND.getMessage(),
                exception.getOutCurrExceptionType().getMessage()
        );
    }

    @Test
    void Given_UserDontHavePermissions_When_GetFacultiesInListFromBanner_Then_ReturnException() {
        doThrow(new OutCurrException(OutCurrExceptionType.FACULTY_FORBIDDEN_FAC_ID))
                .when(facultyValidator).enforceUsrFacForFaculty(any(Long.class), any(UserPermAccess.class));
        assertThrows(OutCurrException.class, () ->bannerFacultyService.getBannerFaculties());
        verify(facultyValidator, times(1)).enforceUsrFacForFaculty(any(Long.class), any(UserPermAccess.class));
        verify(bannerAPI, times(0)).getFacultiesList();
    }

    @Test
    void Given_UserIsAuthenticated_When_GetFacultiesInPageFromBanner_Then_ReturnFacultiesInPage() {
        FacultyInDTO firstFacultyToImport = facultyInDTO();
        FacultyInDTO secondFacultyToImport = secondFacultyInDTO();
        Pageable pageable = PageRequest.of(1,10);
        Page<FacultyInDTO> inDTOPage = new PageImpl<>(
                List.of(firstFacultyToImport, secondFacultyToImport),
                pageable,
                2
        );
        when(bannerAPI.getFacultiesPage(1, 10)).thenReturn(inDTOPage);
        Page<FacultyOutDTO> outDTOPage = bannerFacultyService.getBannerFacultiesPage(1, 10);
        verify(bannerAPI, times(1)).getFacultiesPage(1, 10);
        assertEquals(inDTOPage.getTotalElements(), outDTOPage.getTotalElements());
    }

    @Test
    void Given_UserIsAuthenticated_When_GetFacultiesInPageFromBannerIsEmpty_Then_ThrowException() {
        Pageable pageable = PageRequest.of(1,10);
        Page<FacultyInDTO> inDTOPage = new PageImpl<>(
                List.of(),
                pageable,
                0
        );
        when(bannerAPI.getFacultiesPage(1, 10)).thenReturn(inDTOPage);
        OutCurrException exception =  assertThrows(OutCurrException.class,
                () -> bannerFacultyService.getBannerFacultiesPage(1, 10));
        verify(bannerAPI, times(1)).getFacultiesPage(1, 10);
        assertEquals(
                OutCurrExceptionType.FACULTY_NOT_FOUND.getCode(),
                exception.getOutCurrExceptionType().getCode());
        assertEquals(
                OutCurrExceptionType.FACULTY_NOT_FOUND.getMessage(),
                exception.getOutCurrExceptionType().getMessage()
        );
    }

    @Test
    void Given_UserDontHavePermissions_When_GetFacultiesInPageFromBanner_Then_ReturnException() {
        doThrow(new OutCurrException(OutCurrExceptionType.FACULTY_FORBIDDEN_FAC_ID))
                .when(facultyValidator).enforceUsrFacForFaculty(any(Long.class), any(UserPermAccess.class));
        assertThrows(OutCurrException.class, () ->bannerFacultyService.getBannerFacultiesPage(1, 10));
        verify(facultyValidator, times(1)).enforceUsrFacForFaculty(any(Long.class), any(UserPermAccess.class));
        verify(bannerAPI, times(0)).getFacultiesPage(1, 10);
    }

    @Test
    void Given_UserIsAuthenticated_When_ImportFacultiesFromBanner_Then_AddNewFaculties() {
        FacultyInDTO firstFacultyToImport = facultyInDTO();
        FacultyInDTO secondFacultyToImport = secondFacultyInDTO();
        List<String> facNameSpa = List.of(firstFacultyToImport.facNameSpa(), secondFacultyToImport.facNameSpa());
        when(bannerAPI.importFaculties(facNameSpa)).thenReturn(List.of(firstFacultyToImport, secondFacultyToImport));
        when(facultyProvider.saveFaculty(firstFacultyToImport)).thenReturn(facultyOutDTO());
        when(facultyProvider.saveFaculty(secondFacultyToImport)).thenReturn(secondFacultyOutDTO());
        List<FacultyOutDTO> facultyOutDTOS = bannerFacultyService.importBannerFaculties(facNameSpa);
        verify(bannerAPI, times(1)).importFaculties(facNameSpa);
        verify(facultyProvider, times(facNameSpa.size())).saveFaculty(any());
        assertNotNull(facultyOutDTOS);
        assertEquals(facNameSpa.size(), facultyOutDTOS.size());
        assertEquals(facNameSpa.get(0), facultyOutDTOS.get(0).facNameSpa());
        assertEquals(facNameSpa.get(1), facultyOutDTOS.get(1).facNameSpa());
    }

    @Test
    void Given_UserIsAuthenticated_When_ImportFacultiesNotExists_Then_ThrowException() {
        FacultyInDTO firstFacultyToImport = facultyInDTO();
        List<String> facNameSpa = List.of(firstFacultyToImport.facNameSpa());
        doThrow(new OutCurrException(OutCurrExceptionType.FACULTY_NOT_FOUND))
                .when(bannerAPI).importFaculties(facNameSpa);
        OutCurrException exception =  assertThrows(OutCurrException.class,
                () -> bannerFacultyService.importBannerFaculties(facNameSpa));
        assertEquals(
                OutCurrExceptionType.FACULTY_NOT_FOUND.getCode(),
                exception.getOutCurrExceptionType().getCode());
        assertEquals(
                OutCurrExceptionType.FACULTY_NOT_FOUND.getMessage(),
                exception.getOutCurrExceptionType().getMessage()
        );
    }

    @Test
    void Given_UserDontHavePermissions_When_ImportFacultiesFromBanner_Then_ReturnException() {
        FacultyInDTO firstFacultyToImport = facultyInDTO();
        FacultyInDTO secondFacultyToImport = secondFacultyInDTO();
        List<String> facNameSpa = List.of(firstFacultyToImport.facNameSpa(), secondFacultyToImport.facNameSpa());
        doThrow(new OutCurrException(OutCurrExceptionType.FACULTY_FORBIDDEN_FAC_ID))
                .when(facultyValidator).enforceUsrFacForFaculty(any(Long.class), any(UserPermAccess.class));
        assertThrows(OutCurrException.class, () ->bannerFacultyService.importBannerFaculties(facNameSpa));
        verify(facultyValidator, times(1)).enforceUsrFacForFaculty(any(Long.class), any(UserPermAccess.class));
        verify(bannerAPI, times(0)).importFaculties(facNameSpa);
    }
}
