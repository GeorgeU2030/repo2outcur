package co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="PiAssessmentLevelWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facid}/acad_programs/{acadprogramId}/pi_assessment_lvls")
public interface AuthPiAssessmentLvlController {

}
