package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.TestConfigurationData;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr_mgmt.util.CoursesDummies.getCoursesDummies;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestConfigurationData.class)
@ActiveProfiles(profiles = "test")
@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    // Returns a list of courses when given a valid academic program ID
    @Test
    @Transactional
    void testReturnsCoursesWithAllIdsValid() {
        // Arrange
        long acadProgId = 1;
        long facultyId = 1;
        long acadProgCurrId = 1;
        List<Course> expectedCourses = getCoursesDummies();
        // Act
        List<Course> actualCourses = courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(expectedCourses.size(), actualCourses.size());
        for (int i = 0; i < expectedCourses.size(); i++) {
            assertEquals(expectedCourses.get(i).getCourseId(), actualCourses.get(i).getCourseId());
            assertEquals(expectedCourses.get(i).getCourseNameEng(), actualCourses.get(i).getCourseNameEng());
            assertEquals(expectedCourses.get(i).getSemester().getSemId(), actualCourses.get(i).getSemester().getSemId());
            assertEquals(expectedCourses.get(i).getSemester().getSemName(), actualCourses.get(i).getSemester().getSemName());
        }
    }

    @Test
    @Transactional
    //TODO these three test should be parameterized in one
    void testReturnsCoursesWhenThereAreNoExistingAcadProgWithTheGivenId() {
        // Arrange
        long acadProgId = 2;
        long facultyId = 1;
        long acadProgCurrId = 1;
        // Act
        List<Course> actualCourses = courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(0, actualCourses.size());
    }

    @Test
    @Transactional
    void testReturnsCoursesWhenThereAreNoExistingFacultyWithTheGivenId() {
        // Arrange
        long acadProgId = 1;
        long facultyId = 2;
        long acadProgCurrId = 1;
        // Act
        List<Course> actualCourses = courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(0, actualCourses.size());
    }

    @Test
    @Transactional
    void testReturnsCoursesWhenThereAreNoExistingAcadProgCurrWithTheGivenId() {
        // Arrange
        long acadProgId = 1;
        long facultyId = 1;
        long acadProgCurrId = 2;
        // Act
        List<Course> actualCourses = courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(0, actualCourses.size());
    }
}
