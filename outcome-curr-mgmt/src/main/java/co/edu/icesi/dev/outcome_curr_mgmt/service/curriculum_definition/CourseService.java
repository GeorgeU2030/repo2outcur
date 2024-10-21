package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(long acadProgId, long facultyId, long acadProgCurrId);
}
