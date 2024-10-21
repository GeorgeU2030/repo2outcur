package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.AssmtGenPlanInDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.AssessmentGenPlanStatus;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;

public interface AssessmentGenPlanValidator {


    void enforceUsrFacForAssessmentGenPlan(long facId, UserPermAccess facultyPermAccess,
            AssessmentGenPlanStatus acadProgramPermStatus);

    void enforceUsrPrgForAssessmentGenPlan(long acadProgId, UserPermAccess facultyPermAccess,
            AssessmentGenPlanStatus acadProgramPermStatus);

    void enforceUsrAssmtGenForAssessmentGenPlan(long asgplaId, UserPermAccess facultyPermAccess,
            AssessmentGenPlanStatus acadProgramPermStatus);

    void validateAssmtGenPlanOnAcadProg(long acadProgId, long asgplaId);

    void validateAssmtGenPlanCreation(AssmtGenPlanInDTO assmtGenPlanInDTO);

    AssmtGenPlan validateAssmtGenPlanUpdateDetails(long asgplaId, AssmtGenPlanInDTO assmtGenPlanInDTO);
}
