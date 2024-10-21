package co.edu.icesi.dev.outcome_curr.mgmt.rs.management;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="UsrOutcomeByStudOutcomeWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultytId}/acad_programs/{acadPrgId}/assessemnt_plans/{asgplaId}/"
        + "student_outcomes/{studOutId}/users")
public interface AuthUsrOutcomeByStudOutcomeController {

}
