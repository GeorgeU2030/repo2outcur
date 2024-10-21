package co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.FacultyMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.FacultyRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogServiceImpl;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.matcher.FacultyMatcher;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.FacultyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacultyProviderTest {

    FacultyMapper facultyMapper = Mappers.getMapper(FacultyMapper.class);

    @Mock
    FacultyRepository facultyRepository;

    @Mock
    ChangeLogServiceImpl changeLogService;

    @Mock
    FacultyValidator facultyValidator;

    @InjectMocks
    FacultyProviderImpl facultyProvider;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(facultyProvider, "facultyValidator", facultyValidator);
        ReflectionTestUtils.setField(facultyProvider, "facultyRepository", facultyRepository);
        ReflectionTestUtils.setField(facultyProvider,"changeLogService",changeLogService);
        ReflectionTestUtils.setField(facultyProvider, "facultyMapper", facultyMapper);
    }

    @Test
    void Given_UserIsAuthenticated_When_SendAllRequiredData_Then_FacultyIsSaved() {

        // Arrange
        FacultyInDTO facultyToCreate = facultyInDTO();

        when(facultyRepository.findByFacNameEng(facultyToCreate.facNameEng())).thenReturn(Optional.empty());
        when(facultyRepository.findByFacNameSpa(facultyToCreate.facNameSpa())).thenReturn(Optional.empty());

        // Act
        FacultyOutDTO facultyOutDTO = facultyProvider.saveFaculty(facultyToCreate);

        // Assert
        assertNotNull(facultyOutDTO);

        verify(facultyRepository, times(1)).findByFacNameEng(facultyToCreate.facNameEng());
        verify(facultyRepository, times(1)).findByFacNameSpa(facultyToCreate.facNameSpa());
        verify(facultyRepository, times(1)).save(argThat(new FacultyMatcher(defaultFaculty())));
        verify(changeLogService, times(1)).addChange(any(), any(), any(), any(), any());

    }

    @Test
    void Given_UserIsAuthenticated_When_SendADuplicatedSpaName_Then_SaveFacultyWillFail() {

        // Arrange
        FacultyInDTO facultyToCreate = facultyInDTO();

        when(facultyRepository.findByFacNameSpa(facultyToCreate.facNameSpa())).thenReturn(
                Optional.of(defaultFaculty()));

        try {

            // Act
            facultyProvider.saveFaculty(facultyToCreate);
            fail();

        } catch (OutCurrException e) {

            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();

            // Assert
            assertEquals("There is another faculty with the same name in Spanish", outCurrExceptionType.getMessage());
            assertEquals(4108, outCurrExceptionType.getCode());
            assertEquals("facNameSpa", outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.CONFLICT, outCurrExceptionType.getResponseStatus());
        }
    }

    @Test
    void Given_UserIsAuthenticated_When_SendADuplicatedEngName_Then_SaveFacultyWillFail() {

        // Arrange
        FacultyInDTO facultyToCreate = facultyInDTO();

        when(facultyRepository.findByFacNameEng(facultyToCreate.facNameEng())).thenReturn(
                Optional.of(defaultFaculty()));

        try {

            // Act
            facultyProvider.saveFaculty(facultyToCreate);
            fail();

        } catch (OutCurrException e) {

            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();

            // Assert
            assertEquals("There is another faculty with the same name in English", outCurrExceptionType.getMessage());
            assertEquals(4107, outCurrExceptionType.getCode());
            assertEquals("facNameEng", outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.CONFLICT, outCurrExceptionType.getResponseStatus());
        }
    }

    @Test
    void Given_UserIsAuthenticated_When_SendADuplicatedExternalId_Then_CreateFacultyWillFail() {
        FacultyInDTO facultyToCreate = secondFacultyInDTO();

        when(facultyRepository.findByExternalId(facultyToCreate.externalId())).thenReturn(
                Optional.of(secondFaculty()));

        try {

            // Act
            facultyProvider.saveFaculty(facultyToCreate);
            fail();

        } catch (OutCurrException e) {

            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();

            // Assert
            assertEquals(OutCurrExceptionType.FACULTY_ALREADY_IMPORTED.getMessage(), outCurrExceptionType.getMessage());
            assertEquals(OutCurrExceptionType.FACULTY_ALREADY_IMPORTED.getCode(), outCurrExceptionType.getCode());
            assertEquals(OutCurrExceptionType.FACULTY_ALREADY_IMPORTED.getResponseStatus(), outCurrExceptionType.getResponseStatus());
        }
    }


    @Test
    void GivenAFacId_Then_AFacultyIsFounded() {

        // Arrange
        Faculty facultyToReturn = defaultFaculty();
        when(facultyRepository.findById(15263582L)).thenReturn(Optional.of(facultyToReturn));
        // Act
        Faculty faculty = facultyProvider.findFacultyByFacId(15263582L);
        // Assert
        assertEquals(facultyToReturn.getFacNameEng(), faculty.getFacNameEng());
        assertEquals(facultyToReturn.getFacNameSpa(), faculty.getFacNameSpa());
        assertEquals('Y', faculty.getFacIsActive());
        assertEquals(facultyToReturn.getAcadPrograms().get(0).getAcpId(), faculty.getAcadPrograms().get(0).getAcpId());

    }

    @Test
    void GivenAFacId_Then_AFacultyIsNotFounded() {

        // Arrange
        when(facultyRepository.findById(15263582L)).thenReturn(Optional.empty());

        try {

            // Act
            facultyProvider.findFacultyByFacId(15263582L);
            fail();

        }catch(OutCurrException e){

            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();

            // Assert
            assertEquals("Faculty ID not found",outCurrExceptionType.getMessage());
            assertEquals(4101, outCurrExceptionType.getCode());
            assertEquals("facId",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.NOT_FOUND,outCurrExceptionType.getResponseStatus());

        }
    }

    @Test
    void Given_UserIsAuthenticated_When_SendAFacEngName_Then_AFacultyIsNotFounded() {

        // Arrange
        when(facultyRepository.findByFacNameEng(anyString())).thenReturn(Optional.empty());
        try {

            // Act
            facultyProvider.getFacultyByNameInEng("Health Sciences");
            fail();

        } catch (OutCurrException e) {

            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();

            // Assert
            assertEquals("Faculty name (English) required", outCurrExceptionType.getMessage());
            assertEquals(4102, outCurrExceptionType.getCode());
            assertEquals("facNameEng", outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, outCurrExceptionType.getResponseStatus());

        }

    }

    @Test
    void Given_UserIsAuthenticated_When_SendAFacSpaName_Then_AFacultyIsNotFounded() {

        // Arrange
        when(facultyRepository.findByFacNameSpa(any())).thenReturn(Optional.empty());
        try {

            // Act
            facultyProvider.getFacultyByNameInSpa("Ciencias de la salud");
            fail();

        } catch (OutCurrException e) {

            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();

            // Assert
            assertEquals("Faculty name (Spanish) required", outCurrExceptionType.getMessage());
            assertEquals(4103, outCurrExceptionType.getCode());
            assertEquals("facNameSpa", outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, outCurrExceptionType.getResponseStatus());

        }

    }

    @Test
    void Given_UserIsAuthenticated_When_SendAFacEngName_Then_AFacultyIsFounded() {

        // Arrange
        when(facultyRepository.findByFacNameEng(anyString())).thenReturn(Optional.of(defaultFaculty()));
        // Act
        FacultyOutDTO facultyOutDTO = facultyProvider.getFacultyByNameInEng("Human sciences");
        // Assert
        verify(facultyRepository, times(1)).findByFacNameEng(anyString());
        assertEquals(facultyOutDTO.facId(), defaultFaculty().getFacId());

    }

    @Test
    void Given_UserIsAuthenticated_When_SendAFacSpaName_Then_AFacultyIsFounded() {

        // Arrange
        when(facultyRepository.findByFacNameSpa(anyString())).thenReturn(Optional.of(defaultFaculty()));
        // Act
        FacultyOutDTO facultyOutDTO = facultyProvider.getFacultyByNameInSpa("Ciencias Humanas");
        // Assert
        verify(facultyRepository, times(1)).findByFacNameSpa(anyString());
        assertEquals(facultyOutDTO.facId(), defaultFaculty().getFacId());

    }

}
