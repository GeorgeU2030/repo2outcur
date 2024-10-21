package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.AcadProgramRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrPrgRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import co.edu.icesi.dev.outcome_curr_mgmt.service.component.management.UserProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.BasePermLevel;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.BaseValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.curriculum_qa.AssessmentGenPlanPermType.ASSEMTGENPLAN_FACULTY_PERMISSIONS;
import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType.ACADPROG_ACADPROG_PERMISSIONS;
import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType.ACADPROG_FACULTY_PERMISSIONS;
import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType.AcadProgramPermStatus;

@Service
public class AcadProgramValidatorImpl extends BaseValidator implements AcadProgramValidator {
    private final UsrPrgRepository usrPrgRepository;
    private final AcadProgramRepository acadProgramRepository;
    private final UserProvider userProvider;

    public AcadProgramValidatorImpl(SaamfiJwtTools saamfiJwtTools, UsrPrgRepository usrPrgRepository,
            AcadProgramRepository acadProgramRepository, UserProvider userProvider) {
        super(saamfiJwtTools);
        this.usrPrgRepository = usrPrgRepository;
        this.acadProgramRepository = acadProgramRepository;
        this.userProvider = userProvider;
    }

    private boolean validateUsrPrgForAcadProgram(long acadProgId, UserPermAccess facultyPermAccess,
            AcadProgramPermStatus acadProgramPermStatus) {
        return validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus, BasePermLevel.ANY,
                ACADPROG_ACADPROG_PERMISSIONS) || (
                validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus, BasePermLevel.OWN,
                        ACADPROG_ACADPROG_PERMISSIONS) && usrPrgRepository.findByAcadProgramAcpIdAndUserUsrId(
                        acadProgId, userProvider.getUserIdFromSession()).isPresent());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void enforceUsrPrgForAcadProgram(long acadProgId, UserPermAccess facultyPermAccess,
            AcadProgramPermStatus acadProgramPermStatus) {
        if (!validateUsrPrgForAcadProgram(acadProgId, facultyPermAccess, acadProgramPermStatus)) {
            throw new OutCurrException(OutCurrExceptionType.PROGACAD_FORBIDDEN_PROGRAM_ID);
        }
    }

    private boolean validateUsrFacForAcadProgram(long facId, UserPermAccess facultyPermAccess,
            AcadProgramPermType.AcadProgramPermStatus acadProgramPermStatus) {
        return validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus, BasePermLevel.ANY,
                ACADPROG_FACULTY_PERMISSIONS) || (
                validatePermissionsWithStatus(facultyPermAccess, acadProgramPermStatus, BasePermLevel.OWN,
                        ACADPROG_FACULTY_PERMISSIONS) && usrPrgRepository.findByAcadProgramAcpIdAndUserUsrId(facId,
                        userProvider.getUserIdFromSession()).isPresent());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void enforceUsrFacForAcadProgram(long facId, UserPermAccess facultyPermAccess,
            AcadProgramPermType.AcadProgramPermStatus acadProgramPermStatus) {
        if (!validateUsrFacForAcadProgram(facId, facultyPermAccess, acadProgramPermStatus)) {
            throw new OutCurrException(OutCurrExceptionType.FACULTY_FORBIDDEN_FAC_ID);
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public AcadProgram validatAcadProgOnFaculty(long facId, long acadProgId) {
        return acadProgramRepository.findByFacultyFacIdAndAcpId(facId, acadProgId)
                .orElseThrow(() -> new OutCurrException(OutCurrExceptionType.PROGACAD_CONFLICT_PROGRAM_ID));
    }
}
