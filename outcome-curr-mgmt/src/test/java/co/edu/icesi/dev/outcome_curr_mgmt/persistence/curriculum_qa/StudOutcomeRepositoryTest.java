package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.TestConfigurationData;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr_mgmt.util.SODummies.getStudentOutcomesDummies;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
class StudOutcomeRepositoryTest {

    @Autowired
    private StudOutcomeRepository studOutcomeRepository;

    // Returns a list of courses when given a valid academic program ID
    @Test
    @Transactional
    void testReturnsCoursesWithAllIdsValid() {
        // Arrange
        long acadProgId = 1;
        long facultyId = 1;
        long acadProgCurrId = 1;
        final List<StudOutcome> expectedStudOutcomes = getStudentOutcomesDummies();
        // Act
        List<StudOutcome> actualStudOutcomes = studOutcomeRepository.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(
                acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(expectedStudOutcomes.size(), actualStudOutcomes.size());
        for (int i = 0; i < expectedStudOutcomes.size(); i++) {
            final int indexI = i;
            StudOutcome actualStudOutcome = actualStudOutcomes.stream()
                    .filter(actual -> expectedStudOutcomes.get(indexI).getSoOrdinalNumber() == actual.getSoOrdinalNumber()).toList().get(0);
            assertSoftly(softly -> {
                assertEquals(expectedStudOutcomes.get(indexI).getSoLongNameEng(), actualStudOutcome.getSoLongNameEng());
                assertEquals(expectedStudOutcomes.get(indexI).getSoShortNameEng(),
                        actualStudOutcome.getSoShortNameEng());
                assertEquals(expectedStudOutcomes.get(indexI).getSoOrdinalNumber(),
                        actualStudOutcome.getSoOrdinalNumber());
                assertEquals(expectedStudOutcomes.get(indexI).getPerfIndicators().size(),
                        actualStudOutcome.getPerfIndicators().size());
            });

            for (int j = 0; j < expectedStudOutcomes.get(i).getPerfIndicators().size(); j++) {
                final int indexJ = j;
                assertSoftly(softly -> {
                    assertEquals(expectedStudOutcomes.get(indexI).getPerfIndicators().get(indexJ).getPiId(),
                            actualStudOutcome.getPerfIndicators().get(indexJ).getPiId());
                    assertEquals(expectedStudOutcomes.get(indexI).getPerfIndicators().get(indexJ).getPiLongNameEng(),
                            actualStudOutcome.getPerfIndicators().get(indexJ).getPiLongNameEng());
                    assertEquals(expectedStudOutcomes.get(indexI).getPerfIndicators().get(indexJ).getPiShortNameEng(),
                            actualStudOutcome.getPerfIndicators().get(indexJ).getPiShortNameEng());
                    assertEquals(expectedStudOutcomes.get(indexI).getPerfIndicators().get(indexJ).getPiOrdinalNumber(),
                            actualStudOutcome.getPerfIndicators().get(indexJ).getPiOrdinalNumber());
                });

            }
        }
    }

    @Test
    @Transactional
    void testReturnsCoursesWhenThereAreNoExistingAcadProgWithTheGivenId() {
        // Arrange
        long acadProgId = 2;
        long facultyId = 1;
        long acadProgCurrId = 1;
        // Act
        List<StudOutcome> actualStudOutcomes = studOutcomeRepository.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(
                acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(0, actualStudOutcomes.size());
    }

    @Test
    @Transactional
    //TODO the three tests should be one parameterized
    void testReturnsCoursesWhenThereAreNoExistingFacultyWithTheGivenId() {
        // Arrange
        long acadProgId = 1;
        long facultyId = 2;
        long acadProgCurrId = 1;
        // Act
        List<StudOutcome> actualStudOutcomes = studOutcomeRepository.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(
                acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(0, actualStudOutcomes.size());
    }

    @Test
    @Transactional
    void testReturnsCoursesWhenThereAreNoExistingAcadProgCurrWithTheGivenId() {
        // Arrange
        long acadProgId = 1;
        long facultyId = 1;
        long acadProgCurrId = 2;
        // Act
        List<StudOutcome> actualStudOutcomes = studOutcomeRepository.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(
                acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(0, actualStudOutcomes.size());
    }
}
