package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseServiceTest {

    private CourseRepository courseRepository;
    private CourseService courseService;

    @BeforeEach
    void init() {
        courseRepository = mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository);
    }

    // Returns a list of courses when given a valid academic program ID
    @Test
    void test_returns_list_of_courses_with_valid_academic_program_id() {
        // Arrange
        long acadProgId = 1;
        long facultyId = 1;
        long acadProgCurrId = 1;
        List<Course> expectedCourses = new ArrayList<>();
        expectedCourses.add(new Course());
        expectedCourses.add(new Course());
        when(courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId)).thenReturn(expectedCourses);

        // Act
        List<Course> actualCourses = courseService.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(expectedCourses, actualCourses);
    }

    // Returns an empty list when no courses are found for the given academic program ID
    @Test
    void test_returns_empty_list_when_no_courses_found() {
        // Arrange
        long acadProgId = 1;
        long facultyId = 1;
        long acadProgCurrId = 1;
        List<Course> expectedCourses = new ArrayList<>();
        when(courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId)).thenReturn(expectedCourses);

        // Act
        List<Course> actualCourses = courseService.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(expectedCourses, actualCourses);
    }

    // Returns an empty list when given an invalid academic program ID
    @Test
    void test_returns_empty_list_with_invalid_academic_program_id() {
        // Arrange
        long acadProgId = -1;
        long facultyId = -1;
        long acadProgCurrId = -1;
        List<Course> expectedCourses = new ArrayList<>();
        when(courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId)).thenReturn(expectedCourses);

        // Act
        List<Course> actualCourses = courseService.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(expectedCourses, actualCourses);
    }

    // Returns a course when course ID is zero
    @Test
    void test_retrieve_empty_list_when_zero_id() {
        // Arrange
        long acadProgId = 0;
        long facultyId = 0;
        long acadProgCurrId = 0;
        List<Course> expectedCourses = new ArrayList<>();
        when(courseRepository.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId)).thenReturn(expectedCourses);

        // Act
        List<Course> actualCourses = courseService.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);

        // Assert
        assertEquals(expectedCourses, actualCourses);
    }
}
