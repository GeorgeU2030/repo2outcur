package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.AssmtGenPlanInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.curriculum_qa.AssmtGenPlanOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.mapper.curriculum_qa.AssmtGenPlanMapper;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.AssessmentGenPlanStatus;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.AssmtGenPlanRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.curriculum_qa.AssessmentGenPlanValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.AcadProgramValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static co.edu.icesi.dev.outcome_curr_mgmt.model.enums.AssessmentGenPlanStatus.FUTURE;

@Service
@RequiredArgsConstructor
public class AssessmentGenPlanServiceImpl implements AssessmentGenPlanService {
    //TODO add test coverage
    private final AssessmentGenPlanValidator assessmentGenPlanValidator;
    private final AcadProgramValidator acadProgramValidator;
    private final AssmtGenPlanRepository assmtGenPlanRepository;
    private final AssmtGenPlanMapper assessmentGenPlanMapper;

    @Override
    @Transactional(readOnly = true)
    public AssmtGenPlanOutDTO createAssmtGenPlan(long facultyId, long acadProgId, AssmtGenPlanInDTO assmtGenPlanInDTO) {
        validateAccess(facultyId, acadProgId, UserPermAccess.ADMIN, FUTURE);
        validateStructure(facultyId, acadProgId);
        validateAssmtGenPlanCreation(assmtGenPlanInDTO);
        //TODO create the additional cycles for the assessment plan, subcycles for the cycles and duplicate Outcomes
        // if a source Assessment plan was provided
        return assessmentGenPlanMapper.assmtGenPlanToAssmtGenPlanOutDTO(assmtGenPlanRepository.save(
                assessmentGenPlanMapper.assmtGenPlanInDTOToAssmtGenPlan(assmtGenPlanInDTO)));
    }

    @Override
    @Transactional
    public void updateAssmntGenPlan(long facultyId, long acadProgId, long asgplaId,
            AssmtGenPlanInDTO assmtGenPlanInDTO) {
        AssmtGenPlan assmtGenPlan = validateAssmtGenPlanUpdate(asgplaId, assmtGenPlanInDTO);
        validateAccess(facultyId, acadProgId, asgplaId, UserPermAccess.ADMIN,
                AssessmentGenPlanStatus.fromString(assmtGenPlan.getAsgplaStatus()));
        validateStructure(facultyId, acadProgId, asgplaId);

        assessmentGenPlanMapper.updateAssmtGenPlan(assmtGenPlanInDTO, assmtGenPlan);
        //TODO create the additional cycles for the assessment plan
        assmtGenPlanRepository.save(assmtGenPlan);
    }

    @Override
    @Transactional
    public void updateStatusAssmntGenPlan(long facultyId, long acadProgId, long asgplaId,
            String assessmentGenPlanType) {
        AssmtGenPlan assmtGenPlan = assmtGenPlanRepository.findById(asgplaId)
                .orElseThrow(() -> new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_ASGPLA_ID));
        validateAccess(facultyId, acadProgId, asgplaId, UserPermAccess.UPDATE,
                AssessmentGenPlanStatus.fromString(assmtGenPlan.getAsgplaStatus()));
        validateStructure(facultyId, acadProgId, asgplaId);
        assmtGenPlan.setAsgplaStatus(assessmentGenPlanType);
        //TODO create the additional cycles for the assessment plan
        assmtGenPlanRepository.save(assmtGenPlan);
    }

    @Override
    @Transactional
    public void deleteAssmntGenPlan(long facultyId, long acadProgId, long asgplaId) {
        AssmtGenPlan assmtGenPlan = assmtGenPlanRepository.findById(asgplaId)
                .orElseThrow(() -> new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_ASGPLA_ID));
        validateAccess(facultyId, acadProgId, UserPermAccess.ADMIN,
                AssessmentGenPlanStatus.fromString(assmtGenPlan.getAsgplaStatus()));
        validateStructure(facultyId, acadProgId, asgplaId);
        //TODO we should validate the assessment plan is empty or at least not in use (needs definition) before
        // allowing the deletion
        assmtGenPlanRepository.deleteById(asgplaId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssmtGenPlanOutDTO> findAssesmentGenPlans(long facultyId, long acadProgId,
            String assessmentGenPlanStatus) {
        validateAccess(facultyId, acadProgId, UserPermAccess.QUERY,
                AssessmentGenPlanStatus.fromString(assessmentGenPlanStatus));
        validateStructure(facultyId, acadProgId);
        return assessmentGenPlanMapper.assmtGenPlansToAssmtGenPlanOutDTOs(
                assmtGenPlanRepository.findAllByAcadProgramAcpIdAndAsgplaStatus(acadProgId, assessmentGenPlanStatus));
    }

    @Override
    @Transactional(readOnly = true)
    public AssmtGenPlanOutDTO findAssesmentGenPlan(long facultyId, long acadProgId, long asgplaId,
            String assessmentGenPlanStatus) {
        validateAccess(facultyId, acadProgId, asgplaId, UserPermAccess.QUERY,
                AssessmentGenPlanStatus.fromString(assessmentGenPlanStatus));
        validateStructure(facultyId, acadProgId, asgplaId);
        return assmtGenPlanRepository.findAllByAsgplaIdAndAsgplaStatus(asgplaId, assessmentGenPlanStatus)
                .map(assessmentGenPlanMapper::assmtGenPlanToAssmtGenPlanOutDTO)
                .orElseThrow(() -> new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_ASGPLA_ID));
    }

    private void validateAccess(long facultyId, long acadProgId, UserPermAccess permAccess,
            AssessmentGenPlanStatus permStatus) {
        validateAccess(facultyId, acadProgId, 0L, permAccess, permStatus);
    }

    private void validateAccess(long facultyId, long acadProgId, long asgplaId, UserPermAccess permAccess,
            AssessmentGenPlanStatus permStatus) {
        assessmentGenPlanValidator.enforceUsrFacForAssessmentGenPlan(facultyId, permAccess, permStatus);
        assessmentGenPlanValidator.enforceUsrPrgForAssessmentGenPlan(acadProgId, permAccess, permStatus);
        assessmentGenPlanValidator.enforceUsrAssmtGenForAssessmentGenPlan(asgplaId, permAccess, permStatus);

    }

    private void validateStructure(long facultyId, long acadProgId) {
        validateStructure(facultyId, acadProgId, 0L);
    }

    private void validateStructure(long facultyId, long acadProgId, long asgplaId) {
        acadProgramValidator.validatAcadProgOnFaculty(facultyId, acadProgId);
        assessmentGenPlanValidator.validateAssmtGenPlanOnAcadProg(acadProgId, asgplaId);
    }

    private void validateAssmtGenPlanCreation(AssmtGenPlanInDTO assmtGenPlanInDTO) {
        assessmentGenPlanValidator.validateAssmtGenPlanCreation(assmtGenPlanInDTO);
    }

    private AssmtGenPlan validateAssmtGenPlanUpdate(long asgplaId, AssmtGenPlanInDTO assmtGenPlanInDTO) {
        return assessmentGenPlanValidator.validateAssmtGenPlanUpdateDetails(asgplaId, assmtGenPlanInDTO);
    }
}
