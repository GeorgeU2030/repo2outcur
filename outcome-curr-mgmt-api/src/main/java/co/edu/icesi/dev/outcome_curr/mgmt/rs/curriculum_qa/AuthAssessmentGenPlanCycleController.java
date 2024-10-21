package co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AssessmentGenPlanCycleWebService")
@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/acad_programs/{acadprogId}/assessemnt_plans/"
        + "{asgplaId}/cycles")
public interface AuthAssessmentGenPlanCycleController {

}

