package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.curriculum_qa.AssmtGenPlanInDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.AssessmentGenPlanStatus;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.AssmtGenPlanRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrAssmtGenRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrFacRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrPrgRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import co.edu.icesi.dev.outcome_curr_mgmt.service.component.management.UserProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.BasePermLevel;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.BaseValidator;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.curriculum_qa.AssessmentGenPlanPermType.ASSEMTGENPLAN_ACADPROG_PERMISSIONS;
import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.curriculum_qa.AssessmentGenPlanPermType.ASSEMTGENPLAN_ASSEMTGENPLAN_PERMISSIONS;
import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.curriculum_qa.AssessmentGenPlanPermType.ASSEMTGENPLAN_FACULTY_PERMISSIONS;

@Service
public class AssessmentGenPlanValidatorImpl extends BaseValidator implements AssessmentGenPlanValidator {
    //TODO add test coverage
    private final UsrPrgRepository usrPrgRepository;
    private final UsrFacRepository usrFacRepository;
    private final UsrAssmtGenRepository usrAssmtGenRepository;
    private final UserProvider userProvider;
    private final AssmtGenPlanRepository assmtGenPlanRepository;

    public AssessmentGenPlanValidatorImpl(SaamfiJwtTools saamfiJwtTools, UsrPrgRepository usrPrgRepository,
            UsrFacRepository usrFacRepository, UsrAssmtGenRepository usrAssmtGenRepository,UserProvider userProvider,
            AssmtGenPlanRepository assmtGenPlanRepository) {
        super(saamfiJwtTools);
        this.usrPrgRepository= usrPrgRepository;
        this.usrFacRepository= usrFacRepository;
        this.usrAssmtGenRepository= usrAssmtGenRepository;
        this.userProvider= userProvider;
        this.assmtGenPlanRepository =assmtGenPlanRepository;
    }

    private boolean validateUsrFacForAssessmentGenPlan(long facId, UserPermAccess facultyPermAccess,
            AssessmentGenPlanStatus acadProgramPermStatus) {
        return validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus,
                BasePermLevel.ANY,
                ASSEMTGENPLAN_FACULTY_PERMISSIONS)
                || (
                validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus,
                        BasePermLevel.OWN,
                        ASSEMTGENPLAN_FACULTY_PERMISSIONS)
                        && usrFacRepository.findByFacultyFacIdAndUserUsrId(facId, userProvider.getUserIdFromSession())
                        .isPresent());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void enforceUsrFacForAssessmentGenPlan(long facId, UserPermAccess facultyPermAccess,
            AssessmentGenPlanStatus acadProgramPermStatus) {
        if (!validateUsrFacForAssessmentGenPlan(facId, facultyPermAccess, acadProgramPermStatus)) {
            throw new OutCurrException(OutCurrExceptionType.PROGACAD_FORBIDDEN_PROGRAM_ID);
        }
    }

    private boolean validateUsrPrgForAssessmentGenPlan(long acadProgId, UserPermAccess facultyPermAccess,
            AssessmentGenPlanStatus acadProgramPermStatus) {
        return validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus,
                BasePermLevel.ANY,
                ASSEMTGENPLAN_ACADPROG_PERMISSIONS)
                || (
                validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus,
                        BasePermLevel.OWN,
                        ASSEMTGENPLAN_ACADPROG_PERMISSIONS)
                        && usrPrgRepository.findByAcadProgramAcpIdAndUserUsrId(acadProgId,
                        userProvider.getUserIdFromSession()).isPresent());

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void enforceUsrPrgForAssessmentGenPlan(long acadProgId, UserPermAccess facultyPermAccess,
            AssessmentGenPlanStatus acadProgramPermStatus) {
        if (!validateUsrPrgForAssessmentGenPlan(acadProgId, facultyPermAccess, acadProgramPermStatus)) {
            throw new OutCurrException(OutCurrExceptionType.PROGACAD_FORBIDDEN_PROGRAM_ID);
        }
    }

    private boolean validateUsrAssmtGenForAssessmentGenPlan(long asgplaId, UserPermAccess facultyPermAccess,
            AssessmentGenPlanStatus acadProgramPermStatus) {
        return validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus,
                BasePermLevel.ANY,
                ASSEMTGENPLAN_ASSEMTGENPLAN_PERMISSIONS)
                || (
                validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus,
                        BasePermLevel.OWN,
                        ASSEMTGENPLAN_ASSEMTGENPLAN_PERMISSIONS)
                        && usrAssmtGenRepository.findByAssmtGenPlanAsgplaIdAndUserUsrId(
                        asgplaId, userProvider.getUserIdFromSession()).isPresent());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void enforceUsrAssmtGenForAssessmentGenPlan(long asgplaId, UserPermAccess facultyPermAccess,
            AssessmentGenPlanStatus acadProgramPermStatus) {
        if (!validateUsrAssmtGenForAssessmentGenPlan(asgplaId, facultyPermAccess, acadProgramPermStatus)) {
            throw new OutCurrException(OutCurrExceptionType.PROGACAD_FORBIDDEN_PROGRAM_ID);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void validateAssmtGenPlanOnAcadProg(long acadProgId, long asgplaId) {
        if (asgplaId == 0L) {
            return;
        }
        if (assmtGenPlanRepository.findByAcadProgramAcpIdAndAsgplaId(acadProgId, asgplaId).isEmpty()) {
            throw new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_CONFLICT_ASSMTGENPLAN_ID);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void validateAssmtGenPlanCreation(AssmtGenPlanInDTO assmtGenPlanInDTO) {
        validateAcadPeriodFieds(assmtGenPlanInDTO);
        validateCycleFields(assmtGenPlanInDTO);
    }

    private void validateAcadPeriodFieds(AssmtGenPlanInDTO assmtGenPlanInDTO) {
        OutCurrException outCurrException = null;
        if (assmtGenPlanInDTO.startAcadPeriod() == 0L) {
            outCurrException = new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_START_AC_PERIOD);
        } else if (assmtGenPlanInDTO.endAcadPeriod() == 0L) {
            outCurrException = new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_END_AC_PERIOD);
        } else if (assmtGenPlanInDTO.endAcadPeriod() < assmtGenPlanInDTO.startAcadPeriod()) {
            outCurrException = new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_AC_PERIODS);
        }
        if (outCurrException != null)
            throw outCurrException;
    }

    private void validateCycleFields(AssmtGenPlanInDTO assmtGenPlanInDTO) {
        OutCurrException outCurrException = null;
        if (assmtGenPlanInDTO.numberCycles() > 0 && assmtGenPlanInDTO.numberCycles() <= 10) {
            outCurrException = new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_CYCLES);
        } else if (assmtGenPlanInDTO.subCyclesPerCycles() > 0 && assmtGenPlanInDTO.subCyclesPerCycles() <= 10) {
            outCurrException = new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_SUBCYCLES);
        }
        if (outCurrException != null)
            throw outCurrException;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public AssmtGenPlan validateAssmtGenPlanUpdateDetails(long asgplaId, AssmtGenPlanInDTO assmtGenPlanInDTO) {
        validateAssmtGenPlanCreation(assmtGenPlanInDTO);
        AssmtGenPlan assmtGenPlan = assmtGenPlanRepository.findById(asgplaId)
                .orElseThrow(() -> new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_ASGPLA_ID));
        if (assmtGenPlanInDTO.numberCycles() < assmtGenPlan.getAssmtPlanCycles().size()) {
            throw new OutCurrException(OutCurrExceptionType.ASSMTGENPLAN_INVALID_NEW_CYCLES);
        }
        return assmtGenPlan;
    }

}

