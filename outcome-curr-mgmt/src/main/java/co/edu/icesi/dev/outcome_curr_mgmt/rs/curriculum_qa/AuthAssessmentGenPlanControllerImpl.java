package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.AssmtGenPlanOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa.AuthAssessmentGenPlanController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa.AssessmentGenPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthAssessmentGenPlanControllerImpl implements AuthAssessmentGenPlanController {
    //TODO add test coverage
    private final AssessmentGenPlanService assmtGenPlanService;

    @Override
    public List<AssmtGenPlanOutDTO> getAssmntGenPlan(long facultyId, long acadProgId) {
        return assmtGenPlanService.findAssesmentGenPlans(facultyId, acadProgId, "CURRENT");
    }

    @Override
    public AssmtGenPlanOutDTO getAssmntGenPlanById(long facultyId, long acadProgId, long asgplaId) {
        return assmtGenPlanService.findAssesmentGenPlan(facultyId, acadProgId, asgplaId, "CURRENT");
    }
}
