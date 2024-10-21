package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_definition;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_definition.AuthCourseController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition.CourseService;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthCourseControllerImpl implements AuthCourseController {
    private final CourseService courseService;

}
