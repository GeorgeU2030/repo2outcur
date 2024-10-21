package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.StudOutcomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudOutcomeServiceTest {

    private StudOutcomeRepository studOutcomeRepository;
    private StudOutcomeService studOutcomeService;

    @BeforeEach
    public void init() {
        studOutcomeRepository = mock(StudOutcomeRepository.class);
        studOutcomeService = new StudOutcomeServiceImpl(studOutcomeRepository);
    }

    // Retrieve all StudOutcome objects with valid acadProgCurrId, acadProgId, and facultyId.
    @Test
    void testRetrieveAllStudOutcomesWithValidIds() {
        // Arrange
        long acadProgCurrId = 1;
        long acadProgId = 1;
        long facultyId = 1;

        List<StudOutcome> expectedOutcomes = new ArrayList<>();
        expectedOutcomes.add(new StudOutcome());
        expectedOutcomes.add(new StudOutcome());

        when(studOutcomeRepository.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId)).thenReturn(expectedOutcomes);

        // Act
        List<StudOutcome> actualOutcomes = studOutcomeService.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId);

        // Assert
        assertEquals(expectedOutcomes, actualOutcomes);
    }

    // Retrieve an empty list when no StudOutcome objects are found with valid acadProgCurrId, acadProgId, and facultyId.
    @Test
    void testRetrieveEmptyListWhenNoStudOutcomesFound() {
        // Arrange
        long acadProgCurrId = 1;
        long acadProgId = 1;
        long facultyId = 1;

        List<StudOutcome> expectedOutcomes = new ArrayList<>();

        when(studOutcomeRepository.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId)).thenReturn(expectedOutcomes);

        // Act
        List<StudOutcome> actualOutcomes = studOutcomeService.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId);

        // Assert
        assertEquals(expectedOutcomes, actualOutcomes);
    }

    // Retrieve an empty list when acadProgCurrId, acadProgId, or facultyId is invalid.
    @Test
    void testRetrieveEmptyListWhenInvalidIds() {
        // Arrange
        long acadProgCurrId = -1;
        long acadProgId = -1;
        long facultyId = -1;

        List<StudOutcome> expectedOutcomes = new ArrayList<>();

        when(studOutcomeRepository.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId)).thenReturn(expectedOutcomes);

        // Act
        List<StudOutcome> actualOutcomes = studOutcomeService.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId);

        // Assert
        assertEquals(expectedOutcomes, actualOutcomes);
    }

    // Retrieve an empty list when acadProgCurrId, acadProgId, or facultyId is zero.
    @Test
    void testRetrieveEmptyListWhenZeroIds() {
        // Arrange
        long acadProgCurrId = 0;
        long acadProgId = 0;
        long facultyId = 0;

        List<StudOutcome> expectedOutcomes = new ArrayList<>();

        when(studOutcomeRepository.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId)).thenReturn(expectedOutcomes);

        // Act
        List<StudOutcome> actualOutcomes = studOutcomeService.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId);

        // Assert
        assertEquals(expectedOutcomes, actualOutcomes);
    }
}
