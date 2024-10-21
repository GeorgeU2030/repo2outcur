package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.AssmtGenPlanInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.AssmtGenPlanOutDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssessmentGenPlanService {
    AssmtGenPlanOutDTO createAssmtGenPlan(long facultyId, long acadProgId, AssmtGenPlanInDTO assmtGenPlanInDTO);

    void updateAssmntGenPlan(long facultyId, long acadProgId, long asgplaId, AssmtGenPlanInDTO assmtGenPlanInDTO);

    @Transactional
    void updateStatusAssmntGenPlan(long facultyId, long acadProgId, long asgplaId, String assessmentGenPlanType);

    void deleteAssmntGenPlan(long facultyId, long acadProgId, long asgplaId);

    List<AssmtGenPlanOutDTO> findAssesmentGenPlans(long facultyId, long acadProgId, String assessmentGenPlanStatus);

    AssmtGenPlanOutDTO findAssesmentGenPlan(long facultyId, long acadProgId, long asgplaId,
            String assessmentGenPlanStatus);
}
