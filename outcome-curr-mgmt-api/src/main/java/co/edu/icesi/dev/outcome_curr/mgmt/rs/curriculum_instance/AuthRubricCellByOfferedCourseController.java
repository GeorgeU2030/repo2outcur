package co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_instance;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="RubricCellByOfferedCourseWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/courses/{courseId}/offered_courses/{ofcId}"
        + "/rubric_cells_filled")
public interface AuthRubricCellByOfferedCourseController {

}
