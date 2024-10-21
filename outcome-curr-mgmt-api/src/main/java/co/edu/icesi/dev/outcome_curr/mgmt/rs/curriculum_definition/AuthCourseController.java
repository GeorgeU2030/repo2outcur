package co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_definition;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CoursesWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/courses")
public interface AuthCourseController {

}