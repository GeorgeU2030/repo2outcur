package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.MatrixDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa.AuthCurrMapController;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.curriculum_qa.CurrMapMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition.CourseService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa.CurrMapService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa.StudOutcomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AuthCurrMapControllerTest {

    public static final String TEST_KEY = "test";
    public static final String TEST_VALUE = "test";

    private StudOutcomeService studOutcomeService;
    private CourseService courseService;
    private CurrMapService currMapService;
    private CurrMapMapper currMapMapper;
    private AuthCurrMapController authCurrMapController;

    @BeforeEach
    public void init() {
        studOutcomeService = mock(StudOutcomeService.class);
        courseService = mock(CourseService.class);
        currMapService = mock(CurrMapService.class);
        currMapMapper = mock(CurrMapMapper.class);
        authCurrMapController = new AuthCurrMapControllerImpl(studOutcomeService, courseService, currMapService);
    }

    // Returns a MatrixDTO object with expected values when given valid facultyId, acadProgId, and acadProgCurrId, and no courses.
    @Test
    void testGetMatrix() {
        // Arrange
        long facultyId = 1;
        long acadProgId = 1;
        long acadProgCurrId = 1;

        List<StudOutcome> studentOutcomes = new ArrayList<>();

        List<Course> courses = new ArrayList<>();
        MatrixDTO matrixDTO = MatrixDTO.builder()
                .matrix(new ArrayList<>())
                .build();

        // Mock the service methods
        when(studOutcomeService.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId)).thenReturn(studentOutcomes);
        when(courseService.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId)).thenReturn(courses);
        when(currMapService.getMatrixDTO(acadProgCurrId, acadProgId, studentOutcomes, courses)).thenReturn(matrixDTO);

        // Act
        MatrixDTO result = authCurrMapController.getMatrix(facultyId, acadProgId, acadProgCurrId);

        // Assert
        assertTrue(result.matrix().isEmpty());
        verify(studOutcomeService, times(1)).getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId);
        verify(courseService, times(1)).findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);
        verify(currMapMapper, never()).fromMapElementToValueDTO(any());
    }

    // Parameters passed to the controller method are valid.
    @Test
    void testUpdateSuggestedCurrMapRequestStatus() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long successorCurrMapId = 2;
        String destinationState = "APPROVED";

        doNothing().when(currMapService).updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, successorCurrMapId, destinationState);

        // Act
        authCurrMapController.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, successorCurrMapId, destinationState);

        // Assert
        verify(currMapService, times(1)).updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, successorCurrMapId, destinationState);
    }

    // Destination state is invalid.
    @Test
    void testUpdateSuggestedCurrMapRequestStatusInvalidDestinationState() {
        // Arrange
        long facultyId = 1;
        long programId = 1;
        long acadProgCurrId = 1;
        long prevCurrMapId = 1;
        long successorCurrMapId = 2;
        String destinationState = "INVALID_STATE";

        doThrow(new IllegalArgumentException()).when(currMapService).updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, successorCurrMapId, destinationState);

        // Act
        assertThrows(IllegalArgumentException.class, () -> authCurrMapController.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, successorCurrMapId, destinationState));

        // Assert
        verify(currMapService, times(1)).updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, successorCurrMapId, destinationState);
    }
}
