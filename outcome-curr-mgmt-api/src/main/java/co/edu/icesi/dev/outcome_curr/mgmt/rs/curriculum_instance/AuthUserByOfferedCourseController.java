package co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_instance;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserByOfferedCourseWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/ac_periods/{acPerId}/offered_courses/{ofcId}/users")
public interface AuthUserByOfferedCourseController {

}
