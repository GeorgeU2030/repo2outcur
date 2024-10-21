package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.AcadProgCurriculum;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition.AcadProgCurriculumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AcadProgCurriculumServiceTest {
    private AcadProgCurriculumRepository acadProgCurriculumRepository;
    private AcadProgCurriculumService acadProgCurriculumService;

    @BeforeEach
    public void init() {
        acadProgCurriculumRepository = mock(AcadProgCurriculumRepository.class);
        acadProgCurriculumService = new AcadProgCurriculumServiceImpl(acadProgCurriculumRepository);
    }

    // Returns the AcadProgCurriculum object with the given id.
    @Test
    void testReturnsAcadProgCurriculumWithGivenId() {
        long id = 1;
        AcadProgCurriculum acadProgCurriculum = new AcadProgCurriculum();
        when(acadProgCurriculumRepository.findById(id)).thenReturn(Optional.of(acadProgCurriculum));

        AcadProgCurriculum result = acadProgCurriculumService.findAcadProgCurriculumById(id);

        assertEquals(acadProgCurriculum, result);
    }

    // Returns the first AcadProgCurriculum object when there are multiple objects with the same id.
    @Test
    void testReturnsFirstAcadProgCurriculumWithSameId() {
        long id = 1;
        AcadProgCurriculum acadProgCurriculum1 = new AcadProgCurriculum();
        AcadProgCurriculum acadProgCurriculum2 = new AcadProgCurriculum();
        List<AcadProgCurriculum> acadProgCurriculums = Arrays.asList(acadProgCurriculum1, acadProgCurriculum2);
        when(acadProgCurriculumRepository.findById(id)).thenReturn(Optional.of(acadProgCurriculum1));

        AcadProgCurriculum result = acadProgCurriculumService.findAcadProgCurriculumById(id);

        assertEquals(acadProgCurriculum1, result);
    }

    // Throws a RuntimeException with the message "AcadProgCurriculum not found" when there is no AcadProgCurriculum object with the given id.
    @Test
    void testThrowsRuntimeExceptionWhenAcadProgCurriculumNotFound() {
        long id = 1;
        when(acadProgCurriculumRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> acadProgCurriculumService.findAcadProgCurriculumById(id));
    }

    // Throws a RuntimeException with the message "AcadProgCurriculum not found" when the id parameter is negative.
    @Test
    void testThrowsRuntimeExceptionWhenIdIsNegative() {
        long id = -1;

        assertThrows(RuntimeException.class, () -> acadProgCurriculumService.findAcadProgCurriculumById(id));
    }

    // Throws a RuntimeException with the message "AcadProgCurriculum not found" when the id parameter is zero.
    @Test
    void testThrowsRuntimeExceptionWhenIdIsZero() {
        long id = 0;

        assertThrows(RuntimeException.class, () -> acadProgCurriculumService.findAcadProgCurriculumById(id));
    }

    // Returns the AcadProgCurriculum object with the highest id when the id parameter is Long.MAX_VALUE.
    @Test
    void testReturnsAcadProgCurriculumWithHighestId() {
        long id = Long.MAX_VALUE;
        AcadProgCurriculum acadProgCurriculum = new AcadProgCurriculum();
        when(acadProgCurriculumRepository.findById(id)).thenReturn(Optional.of(acadProgCurriculum));

        AcadProgCurriculum result = acadProgCurriculumService.findAcadProgCurriculumById(id);

        assertEquals(acadProgCurriculum, result);
    }

    // Returns the AcadProgCurriculum object with the lowest id when the id parameter is Long.MIN_VALUE.
    @Test
    void testReturnsAcadProgCurriculumWithLowestId() {
        long id = Long.MIN_VALUE;
        AcadProgCurriculum acadProgCurriculum = new AcadProgCurriculum();
        when(acadProgCurriculumRepository.findById(id)).thenReturn(Optional.of(acadProgCurriculum));

        AcadProgCurriculum result = acadProgCurriculumService.findAcadProgCurriculumById(id);

        assertEquals(acadProgCurriculum, result);
    }
}
