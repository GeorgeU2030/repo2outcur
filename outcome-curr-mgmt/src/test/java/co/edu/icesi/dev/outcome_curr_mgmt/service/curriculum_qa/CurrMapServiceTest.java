package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.MatrixDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.ValueDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.curriculum_qa.CurrMapMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.CurrMap;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.CurrMapRequestStatus;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.CurrMapRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.component.management.UserProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.util.CoursesDummies;
import co.edu.icesi.dev.outcome_curr_mgmt.util.CurrMapsDummies;
import co.edu.icesi.dev.outcome_curr_mgmt.util.SODummies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_qa.AuthCurrMapControllerTest.TEST_KEY;
import static co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_qa.AuthCurrMapControllerTest.TEST_VALUE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CurrMapServiceTest {

    private CurrMapService currMapService;
    private CurrMapRepository currMapRepository;
    private UserProvider userProvider;
    private CurrMapMapper currMapMapper;
    private ChangeLogService changeLogService;

    @BeforeEach
    public void init() {
        currMapMapper = mock(CurrMapMapper.class);
        currMapRepository = mock(CurrMapRepository.class);
        userProvider = mock(UserProvider.class);
        changeLogService = mock(ChangeLogService.class);
        currMapService = new CurrMapServiceImpl(currMapRepository, currMapMapper, userProvider, changeLogService);
    }

    // Returns a MatrixDTO object with expected values when given valid facultyId, acadProgId, acadProgCurrId, courses, studOutcomes, and currMaps.
    @Test
    void testExistentStudOutcomesAndCoursesAndCurrMapsGivenIds() {
        // Arrange
        long acadProgId = 1;
        long acadProgCurrId = 1;

        List<StudOutcome> studOutcomes = SODummies.getStudentOutcomesDummies();
        List<Course> courses = CoursesDummies.getCoursesDummiesWithCoursesInTheSameSemester();
        List<CurrMap> currMaps = CurrMapsDummies.getCurrMapsDummies();

        ValueDTO valueDTO = ValueDTO.builder()
                .key(TEST_KEY)
                .value(TEST_VALUE)
                .build();

        // Mock the repository and mapper methods
        when(currMapRepository.getAllByAcadProgCurriculumApcIdAndPerfIndicatorPiIdAndCourseCourseIdAndAcadProgCurriculumAcadProgramAcpIdOrderByCmAcceptedDateDesc(1L, 1L, 1L, 1L)).thenReturn(List.of(currMaps.get(0)));
        when(currMapRepository.getAllByAcadProgCurriculumApcIdAndPerfIndicatorPiIdAndCourseCourseIdAndAcadProgCurriculumAcadProgramAcpIdOrderByCmAcceptedDateDesc(1L, 2L, 1L, 1L)).thenReturn(List.of(currMaps.get(1)));
        when(currMapRepository.getAllByAcadProgCurriculumApcIdAndPerfIndicatorPiIdAndCourseCourseIdAndAcadProgCurriculumAcadProgramAcpIdOrderByCmAcceptedDateDesc(1L, 1L, 2L, 1L)).thenReturn(List.of(currMaps.get(2)));
        when(currMapRepository.getAllByAcadProgCurriculumApcIdAndPerfIndicatorPiIdAndCourseCourseIdAndAcadProgCurriculumAcadProgramAcpIdOrderByCmAcceptedDateDesc(1L, 2L, 2L, 1L)).thenReturn(List.of(currMaps.get(3)));
        when(currMapRepository.getAllByAcadProgCurriculumApcIdAndPerfIndicatorPiIdAndCourseCourseIdAndAcadProgCurriculumAcadProgramAcpIdOrderByCmAcceptedDateDesc(1L, 3L, 2L, 1L)).thenReturn(List.of(currMaps.get(4)));
        when(currMapMapper.fromMapElementToValueDTO(any())).thenReturn(valueDTO);

        // Act
        MatrixDTO result = currMapService.getMatrixDTO(acadProgCurrId, acadProgId, studOutcomes, courses);

        // Assert
        assertFalse(result.matrix().isEmpty());
        assertEquals(4, result.matrix().size());
        result.matrix().forEach(row -> assertEquals(8, row.cells().size()));
        assertTrue(result.matrix().get(0).cells().get(0).values().isEmpty());
        assertTrue(result.matrix().get(0).cells().get(1).values().isEmpty());
        for (int i = 2; i < result.matrix().get(0).cells().size(); i++) {
            assertFalse(result.matrix().get(0).cells().get(i).values().isEmpty());
        }
        for (int i = 0; i < 4 ; i++) {
            assertFalse(result.matrix().get(1).cells().get(i).values().isEmpty());
        }
        for (int i = 4; i < result.matrix().get(1).cells().size() ; i++) {
            assertTrue(result.matrix().get(1).cells().get(i).values().isEmpty());
        }
        for (int i = 0; i < 2 ; i++) {
            assertFalse(result.matrix().get(2).cells().get(i).values().isEmpty());
        }
        for (int i = 2; i < result.matrix().get(2).cells().size() ; i++) {
            assertTrue(result.matrix().get(2).cells().get(i).values().isEmpty());
        }
        for (int i = 0; i < 5 ; i++) {
            assertFalse(result.matrix().get(3).cells().get(i).values().isEmpty());
        }
        for (int i = 5; i < result.matrix().get(2).cells().size() ; i++) {
            assertTrue(result.matrix().get(3).cells().get(i).values().isEmpty());
        }

        verify(currMapMapper, times(37)).fromMapElementToValueDTO(any());
    }

    // Returns a MatrixDTO object with expected values when given valid facultyId, acadProgId, and acadProgCurrId, but no studOutcomes, courses, or currMaps.
    @Test
    void testNonExistentCoursesAndStudOutcomesGivenIds() {
        // Arrange
        long acadProgId = 1;
        long acadProgCurrId = 1;

        List<StudOutcome> studentOutcomes = new ArrayList<>();

        List<Course> courses = new ArrayList<>();

        // Act
        MatrixDTO result = currMapService.getMatrixDTO(acadProgCurrId, acadProgId, studentOutcomes, courses);

        // Assert
        assertTrue(result.matrix().isEmpty());
        verify(currMapMapper, never()).fromMapElementToValueDTO(any());
    }

    // Returns a MatrixDTO object with expected values when given valid facultyId, acadProgId, and acadProgCurrId, and no studOutcomes.
    @Test
    void testNonExistentStudOutcomesGivenIds() {
        // Arrange
        long acadProgId = 1;
        long acadProgCurrId = 1;

        List<StudOutcome> studOutcomes = new ArrayList<>();

        List<Course> courses = CoursesDummies.getCoursesDummies();

        // Mock the mapper methods
        when(currMapMapper.fromMapElementToValueDTO(any())).thenReturn(null);

        // Act
        MatrixDTO result = currMapService.getMatrixDTO(acadProgCurrId, acadProgId, studOutcomes, courses);

        // Assert
        assertFalse(result.matrix().isEmpty());
        assertEquals(2, result.matrix().size());
        result.matrix().forEach(row -> assertEquals(2, row.cells().size()));
        verify(currMapMapper, times(6)).fromMapElementToValueDTO(any());
    }

    // Returns a MatrixDTO object with expected values when given valid facultyId, acadProgId, and acadProgCurrId, and no currMaps.
    @Test
    void testExistentStudOutcomesAndCoursesGivenIds() {
        // Arrange
        long acadProgId = 1;
        long acadProgCurrId = 1;

        List<StudOutcome> studOutcomes = SODummies.getStudentOutcomesDummies();
        List<Course> courses = CoursesDummies.getCoursesDummies();

        // Mock the repository and mapper methods
        when(currMapRepository.getAllByAcadProgCurriculumApcIdAndPerfIndicatorPiIdAndCourseCourseIdAndAcadProgCurriculumAcadProgramAcpIdOrderByCmAcceptedDateDesc(anyLong(), anyLong(), anyLong(), anyLong())).thenReturn(new ArrayList<>());
        when(currMapMapper.fromMapElementToValueDTO(any())).thenReturn(null);

        // Act
        MatrixDTO result = currMapService.getMatrixDTO(acadProgCurrId, acadProgId, studOutcomes, courses);

        // Assert
        assertFalse(result.matrix().isEmpty());
        assertEquals(3, result.matrix().size());
        result.matrix().forEach(row -> assertEquals(8, row.cells().size()));
        verify(currMapMapper, times(24)).fromMapElementToValueDTO(any());
    }

    // The method updates the status of a suggested curriculum map to 'APPROVED' and sets the previous curriculum map to 'REPLACED'.
    @Test
    void testUpdateSuggestedCurrMapRequestStatus_Approved() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long suggestedCurrMapId = 2;
        String destinationState = "APPROVED";

        String requestState1 = CurrMapRequestStatus.APPROVED.getKey();

        CurrMap prevCurrMap = new CurrMap();
        prevCurrMap.setCmId(prevCurrMapId);
        prevCurrMap.setRequestState(requestState1);
        prevCurrMap.setUser2(new User());

        String requestState2 = CurrMapRequestStatus.PENDING.getKey();

        CurrMap suggestedCurrMap = new CurrMap();
        suggestedCurrMap.setCmId(suggestedCurrMapId);
        suggestedCurrMap.setRequestState(requestState2);

        when(currMapRepository.findById(prevCurrMapId)).thenReturn(Optional.of(prevCurrMap));
        when(currMapRepository.findById(suggestedCurrMapId)).thenReturn(Optional.of(suggestedCurrMap));
        when(userProvider.getUserFromSession()).thenReturn(new User());

        // Act
        currMapService.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, suggestedCurrMapId, destinationState);

        // Assert
        assertEquals(CurrMapRequestStatus.REPLACED.getKey(), prevCurrMap.getRequestState());
        assertNotNull(suggestedCurrMap.getCmAcceptedDate());
        assertNull(suggestedCurrMap.getCmRejectedDate());
        assertNotNull(suggestedCurrMap.getUser2());
        assertNotNull(prevCurrMap.getUser2());
        assertEquals(destinationState, suggestedCurrMap.getRequestState());
        verify(currMapRepository, times(2)).findById(anyLong());
        verify(currMapRepository, times(2)).save(any(CurrMap.class));
        verify(changeLogService, times(1)).addChange(any(), anyString(), anyString(), any(), any());
    }

    // The method updates the status of a suggested curriculum map to 'PENDING'.
    @Test
    void testUpdateSuggestedCurrMapRequestStatus_Pending() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long suggestedCurrMapId = 2;
        String destinationState = "PENDING";

        String requestState1 = CurrMapRequestStatus.APPROVED.getKey();

        CurrMap prevCurrMap = new CurrMap();
        prevCurrMap.setCmId(prevCurrMapId);
        prevCurrMap.setRequestState(requestState1);
        prevCurrMap.setUser2(new User());

        String requestState2 = CurrMapRequestStatus.PENDING.getKey();

        CurrMap suggestedCurrMap = new CurrMap();
        suggestedCurrMap.setCmId(suggestedCurrMapId);
        suggestedCurrMap.setRequestState(requestState2);

        when(currMapRepository.findById(prevCurrMapId)).thenReturn(Optional.of(prevCurrMap));
        when(currMapRepository.findById(suggestedCurrMapId)).thenReturn(Optional.of(suggestedCurrMap));
        when(userProvider.getUserFromSession()).thenReturn(new User());

        // Act
        currMapService.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, suggestedCurrMapId, destinationState);

        // Assert
        assertNull(suggestedCurrMap.getCmAcceptedDate());
        assertNull(suggestedCurrMap.getCmRejectedDate());
        assertNotNull(suggestedCurrMap.getUser2());
        assertEquals(destinationState, suggestedCurrMap.getRequestState());
        verify(currMapRepository, times(2)).findById(anyLong());
        verify(currMapRepository, times(1)).save(any(CurrMap.class));
        verify(changeLogService, times(1)).addChange(any(), anyString(), anyString(), any(), any());
    }

    // The method updates the status of a suggested curriculum map to 'REJECTED'.
    @Test
    void testUpdateSuggestedCurrMapRequestStatus_Rejected() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long suggestedCurrMapId = 2;
        String destinationState = "REJECTED";

        String requestState1 = CurrMapRequestStatus.APPROVED.getKey();

        CurrMap prevCurrMap = new CurrMap();
        prevCurrMap.setCmId(prevCurrMapId);
        prevCurrMap.setRequestState(requestState1);
        prevCurrMap.setUser2(new User());

        String requestState2 = CurrMapRequestStatus.PENDING.getKey();

        CurrMap suggestedCurrMap = new CurrMap();
        suggestedCurrMap.setCmId(suggestedCurrMapId);
        suggestedCurrMap.setRequestState(requestState2);

        when(currMapRepository.findById(prevCurrMapId)).thenReturn(Optional.of(prevCurrMap));
        when(currMapRepository.findById(suggestedCurrMapId)).thenReturn(Optional.of(suggestedCurrMap));
        when(userProvider.getUserFromSession()).thenReturn(new User());

        // Act
        currMapService.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, suggestedCurrMapId, destinationState);

        // Assert
        assertNotNull(suggestedCurrMap.getCmRejectedDate());
        assertNull(suggestedCurrMap.getCmAcceptedDate());
        assertNotNull(suggestedCurrMap.getUser2());
        assertEquals(destinationState, suggestedCurrMap.getRequestState());
        verify(currMapRepository, times(2)).findById(anyLong());
        verify(currMapRepository, times(1)).save(any(CurrMap.class));
        verify(changeLogService, times(1)).addChange(any(), anyString(), anyString(), any(), any());
    }

    // The suggested curriculum map was already approved, and an exception is thrown.
    @Test
    void testSuggestedCurrMapAlreadyApprovedWhenApprovingIt() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long suggestedCurrMapId = 2;
        String destinationState = "APPROVED";

        CurrMap suggestedCurrMap = new CurrMap();
        suggestedCurrMap.setCmId(suggestedCurrMapId);
        suggestedCurrMap.setRequestState(CurrMapRequestStatus.APPROVED.getKey());

        when(currMapRepository.findById(suggestedCurrMapId)).thenReturn(Optional.of(suggestedCurrMap));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> currMapService.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, suggestedCurrMapId, destinationState));
    }

    // The suggested curriculum map was already rejected, and an exception is thrown.
    @Test
    void testSuggestedCurrMapAlreadyRejectedWhenRejectingIt() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long suggestedCurrMapId = 2;
        String destinationState = "REJECTED";

        CurrMap suggestedCurrMap = new CurrMap();
        suggestedCurrMap.setCmId(suggestedCurrMapId);
        suggestedCurrMap.setRequestState(CurrMapRequestStatus.REJECTED.getKey());

        when(currMapRepository.findById(suggestedCurrMapId)).thenReturn(Optional.of(suggestedCurrMap));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> currMapService.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, suggestedCurrMapId, destinationState));
    }

    // The previous curriculum map does not exist, and an exception is thrown
    @Test
    void testPrevCurrMapNotExist() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long suggestedCurrMapId = 2;
        String destinationState = "APPROVED";

        CurrMap suggestedCurrMap = new CurrMap();
        suggestedCurrMap.setCmId(suggestedCurrMapId);
        suggestedCurrMap.setRequestState(CurrMapRequestStatus.PENDING.getKey());

        when(currMapRepository.findById(prevCurrMapId)).thenReturn(Optional.empty());
        when(currMapRepository.findById(suggestedCurrMapId)).thenReturn(Optional.of(suggestedCurrMap));

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> currMapService.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, suggestedCurrMapId, destinationState));
    }

    // The suggested curriculum map does not exist, and an exception is thrown.
    @Test
    void testNonExistentSuggestedCurrMap() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long successorCurrMapId = 2;
        String destinationState = "APPROVED";

        when(currMapRepository.findById(successorCurrMapId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> currMapService.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, successorCurrMapId, destinationState));
    }

    // The destination state is invalid, and an exception is thrown.
    @Test
    void testInvalidDestinationState() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long suggestedCurrMapId = 2;
        String destinationState = "INVALID_STATE";

        CurrMap prevCurrMap = new CurrMap();
        prevCurrMap.setCmId(prevCurrMapId);

        CurrMap suggestedCurrMap = new CurrMap();
        suggestedCurrMap.setCmId(suggestedCurrMapId);

        when(currMapRepository.findById(prevCurrMapId)).thenReturn(Optional.of(prevCurrMap));
        when(currMapRepository.findById(suggestedCurrMapId)).thenReturn(Optional.of(suggestedCurrMap));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> currMapService.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, suggestedCurrMapId, destinationState));
    }
}