package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.faculty.FacultyMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.FacultyRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogServiceImpl;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.matcher.FacultyMatcher;
import co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty.FacultyProviderImpl;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.FacultyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static co.edu.icesi.dev.outcome_curr_mgmt.util.OutcomeCurrMgmtUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {
    FacultyMapper facultyMapper = Mappers.getMapper(FacultyMapper.class);
    @Mock
    FacultyProviderImpl facultyProvider;
    @Mock
    FacultyRepository facultyRepository;
    @InjectMocks
    FacultyServiceImpl facultyService;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(facultyService, "facultyMapper", facultyMapper);
        ReflectionTestUtils.setField(facultyService, "facultyProvider", facultyProvider);
        ReflectionTestUtils.setField(facultyService, "facultyRepository", facultyRepository);
    }

    @Test
    void GivenAllRequiredData_Then_AFacultyIsSaved() {

        // Arrange
        FacultyInDTO facultyToCreate = facultyInDTO();

        when(facultyProvider.saveFaculty(facultyToCreate)).thenReturn(facultyOutDTO());

        // Act
        FacultyOutDTO facultyOutDTO = facultyService.createFaculty(facultyToCreate);

        // Assert

        assertEquals(facultyToCreate.facNameEng(), facultyOutDTO.facNameEng());
        assertEquals(facultyToCreate.facNameSpa(), facultyOutDTO.facNameSpa());
        assertEquals('Y', facultyOutDTO.facIsActive());
    }

    @Test
    void GivenBadData_When_SavingAFaculty_Then_AExceptionIsThrown(){

        // Arrange
        FacultyInDTO facultyToCreate = facultyInDTO();

        when(facultyProvider.saveFaculty(facultyToCreate)).thenThrow(
                new OutCurrException(OutCurrExceptionType.FACULTY_DUPLICATED_FAC_NAME_SPA));

        try{

            // Act
            facultyService.createFaculty(facultyToCreate);
            fail();

        }catch (OutCurrException e){

            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();

            // Assert
            assertEquals("There is another faculty with the same name in Spanish", outCurrExceptionType.getMessage());
            assertEquals(4108, outCurrExceptionType.getCode());
            assertEquals("facNameSpa",outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.CONFLICT,outCurrExceptionType.getResponseStatus());
        }
    }

    @Test
    void Given_UserIsAuthenticated_When_SendAllRequiredData_Then_FacultyIsUpdated(){

        // Arrange
        FacultyInDTO facultyToUpdate = facultyToUpdateDTO();

        when(facultyProvider.findFacultyByFacId(15263582L)).thenReturn(defaultFaculty());

        // Act
        FacultyOutDTO facultyOutDTO = facultyService.updateFaculty(15263582L, facultyToUpdate);

        // Assert
        assertNotNull(facultyOutDTO);
        assertEquals(facultyOutDTO.facIsActive(), 'N');
        assertEquals(facultyOutDTO.facNameEng(), "Human sciences and Psychology");
        assertEquals(facultyOutDTO.facNameSpa(),"Ciencias Humanas y psicología");
        verify(facultyProvider, times(1)).checkIfEngNameIsAlreadyUsed(facultyToUpdate.facNameEng());
        verify(facultyProvider, times(1)).checkIfSpaNameIsAlreadyUsed(facultyToUpdate.facNameSpa());
        verify(facultyProvider, times(1)).findFacultyByFacId(15263582L);
    }

    @Test
    void Given_UserIsAuthenticated_When_SendAFacId_Then_ItIsDeleted() {

        // Arrange
        when(facultyProvider.findFacultyByFacId(anyLong())).thenReturn(thirdFaculty());
        // Act
        facultyService.deleteFaculty(15263582L);
        // Assert
        verify(facultyProvider, times(1)).findFacultyByFacId(anyLong());
        verify(facultyRepository, times(1)).delete(any());
    }

    @Test
    void Given_UserIsAuthenticated_When_SendAFacId_ThatHasPrograms_Then_ItIsNotDeleted() {

        // Arrange
        when(facultyProvider.findFacultyByFacId(anyLong())).thenReturn(defaultFaculty());

        try {

            // Act
            facultyService.deleteFaculty(15263582L);
            fail();

        } catch (OutCurrException e) {

            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();

            // Assert
            assertEquals("A faculty cannot be deleted if has academic programs, courses or users associated.", outCurrExceptionType.getMessage());
            assertEquals(4109, outCurrExceptionType.getCode());
            assertEquals("facId", outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.CONFLICT, outCurrExceptionType.getResponseStatus());

            verify(facultyProvider, times(1)).findFacultyByFacId(anyLong());
            verify(facultyRepository, never()).delete(any());

        }
    }

    @Test
    void Given_UserIsAuthenticated_When_SendAFacId_ThatHasUsers_Then_ItIsDeleted() {

        // Arrange
        Faculty facultyToDelete = defaultFaculty();
        facultyToDelete.setAcadPrograms(List.of());
        facultyToDelete.setUsers(List.of(testUser()));
        facultyToDelete.setUsrFacs(List.of(testUsrFac()));
        when(facultyProvider.findFacultyByFacId(anyLong())).thenReturn(facultyToDelete);
        // Act
        facultyService.deleteFaculty(15263582L);
        // Assert
        verify(facultyProvider, times(1)).findFacultyByFacId(anyLong());
        verify(facultyRepository, times(1)).delete(any());
        assertEquals(facultyRepository.findAll().size(), 0);
    }

    @Test
    void Given_UserIsAuthenticated_When_SendAFacId_Then_AFacultyIsFounded() {

        // Arrange
        when(facultyProvider.findFacultyByFacId(anyLong())).thenReturn(defaultFaculty());
        // Act
        facultyService.getFacultyByFacId(15263582L);
        // Assert
        verify(facultyProvider, times(1)).findFacultyByFacId(anyLong());

    }

    @Test
    void Given_UserIsAuthenticated_When_SendAFacId_Then_AFacultyIsNotFounded() {

        // Arrange
        when(facultyProvider.findFacultyByFacId(anyLong())).thenThrow(new OutCurrException(OutCurrExceptionType.FACULTY_INVALID_FAC_ID));
        try {

            // Act
            facultyService.getFacultyByFacId(15263582L);
            fail();

        } catch (OutCurrException e) {

            OutCurrExceptionType outCurrExceptionType = e.getOutCurrExceptionType();

            // Assert
            assertEquals("Faculty ID not found", outCurrExceptionType.getMessage());
            assertEquals(4101, outCurrExceptionType.getCode());
            assertEquals("facId", outCurrExceptionType.getParameterName());
            assertEquals(HttpStatus.NOT_FOUND, outCurrExceptionType.getResponseStatus());

            verify(facultyProvider, times(1)).findFacultyByFacId(anyLong());
        }

    }

    @Test
    void Given_UserIsAuthenticated_When_WantsToGetAllFaculties_Then_FacultiesAreReturned() {

        // Arrange
        when(facultyRepository.findAll()).thenReturn(List.of(defaultFaculty(), secondFaculty()));
        //Act
        List<FacultyOutDTO> facultiesOutDTO = facultyService.getFaculties();

        // Assert
        assertEquals(2, facultiesOutDTO.size());
        assertEquals(15263582L, facultiesOutDTO.get(0).facId());
        assertEquals(15263692L, facultiesOutDTO.get(1).facId());

        verify(facultyRepository, times(1)).findAll();

    }
}
