package co.edu.icesi.dev.outcome_curr_mgmt.util;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Semester;

import java.util.ArrayList;
import java.util.List;

public class CoursesDummies {

    public static final String SEMESTER_4 = "4";
    public static final String SEMESTER_5 = "5";
    public static final String COURSE_NAME_ENG_1 = "Internet Computing I";
    public static final String COURSE_NAME_SPA_1 = "Computación en internet I";
    public static final String COURSE_NAME_ENG_2 = "Capstone project I";
    public static final String COURSE_NAME_SPA_2 = "Proyecto Integrador I";
    public static final String COURSE_NAME_ENG_3 = "Applied Mathematics III";
    public static final String COURSE_NAME_SPA_3 = "Matemáticas aplicadas III";

    public static List<Course> getCoursesDummies() {
        List<Course> courses = new ArrayList<>();

        Semester semester4 = Semester.builder()
                .semId(1L)
                .semName(SEMESTER_4)
                .build();

        Semester semester5 = Semester.builder()
                .semId(2L)
                .semName(SEMESTER_5)
                .build();

        Course course1 = Course.builder()
                .courseId(1L)
                .courseNameEng(COURSE_NAME_ENG_1)
                .courseNameSpa(COURSE_NAME_SPA_1)
                .semester(semester4)
                .build();

        Course course2 = Course.builder()
                .courseId(2L)
                .courseNameEng(COURSE_NAME_ENG_2)
                .courseNameSpa(COURSE_NAME_SPA_2)
                .semester(semester5)
                .build();

        courses.add(course1);
        courses.add(course2);

        return courses;
    }

    public static List<Course> getCoursesDummiesWithCoursesInTheSameSemester() {
        List<Course> courses = new ArrayList<>();

        Semester semester4 = Semester.builder()
                .semId(1L)
                .semName(SEMESTER_4)
                .build();

        Semester semester5 = Semester.builder()
                .semId(2L)
                .semName(SEMESTER_5)
                .build();

        Course course1 = Course.builder()
                .courseId(1L)
                .courseNameEng(COURSE_NAME_ENG_1)
                .courseNameSpa(COURSE_NAME_SPA_1)
                .semester(semester4)
                .build();

        Course course2 = Course.builder()
                .courseId(2L)
                .courseNameEng(COURSE_NAME_ENG_2)
                .courseNameSpa(COURSE_NAME_SPA_2)
                .semester(semester5)
                .build();

        Course course3 = Course.builder()
                .courseId(3L)
                .courseNameEng(COURSE_NAME_ENG_3)
                .courseNameSpa(COURSE_NAME_SPA_3)
                .semester(semester5)
                .build();

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        return courses;
    }
}
