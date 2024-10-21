package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.AssmtGenPlanInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.AssmtGenPlanOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa.AdminAsssessmentGenPlanController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa.AssessmentGenPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminAssessmentGenPlanControllerImpl implements AdminAsssessmentGenPlanController {
    //TODO add test coverage
    private final AssessmentGenPlanService assmtGenPlanService;

    @Override
    public AssmtGenPlanOutDTO createAssessmentGenPlan(long facultyId, long acadProgId,
            AssmtGenPlanInDTO assmtGenPlanInDTO) {
        return assmtGenPlanService.createAssmtGenPlan(facultyId, acadProgId, assmtGenPlanInDTO);
    }

    @Override
    public void updateAssessmentGenPlanById(long facultyId, long acadProgId, long asgplaId,
            AssmtGenPlanInDTO assmtGenPlanInDTO) {
        assmtGenPlanService.updateAssmntGenPlan(facultyId, acadProgId, asgplaId, assmtGenPlanInDTO);
    }

    @Override
    public void updateStatusAssmntGenPlan(long facultyId, long acadProgId, long asgplaId,
            String assessmentGenPlanType) {
        assmtGenPlanService.updateStatusAssmntGenPlan(facultyId, acadProgId, asgplaId, assessmentGenPlanType);
    }

    @Override
    public void deleteAssessmentGenPlanById(long facultyId, long acadProgId, long asgplaId) {
        assmtGenPlanService.deleteAssmntGenPlan(facultyId, acadProgId, asgplaId);
    }
}
