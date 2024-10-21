package co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="AssessmentTypeWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/acad_programs/{acadprogId}/assessment_types")
public interface AuthAssessmentTypeController {

}
