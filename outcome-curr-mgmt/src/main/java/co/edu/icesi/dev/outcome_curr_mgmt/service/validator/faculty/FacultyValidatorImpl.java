package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrFacRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import co.edu.icesi.dev.outcome_curr_mgmt.service.component.management.UserProvider;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.BasePermLevel;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.BaseValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.FacultyPermType.FAC_FACULTY_PERMISSIONS;

@Service
public class FacultyValidatorImpl extends BaseValidator implements FacultyValidator {

    private final UsrFacRepository usrFacRepository;
    private final UserProvider userProvider;

    public FacultyValidatorImpl(SaamfiJwtTools saamfiJwtTools, UsrFacRepository usrFacRepository,
            UserProvider userProvider) {
        super(saamfiJwtTools);
        this.usrFacRepository = usrFacRepository;
        this.userProvider = userProvider;
    }

    private boolean validateUsrFacForFaculty(long facId, UserPermAccess facultyPermAccess) {
        return validatePermissionsWithoutStatus(facultyPermAccess, BasePermLevel.ANY,
                FAC_FACULTY_PERMISSIONS) || (
                validatePermissionsWithoutStatus(facultyPermAccess, BasePermLevel.OWN,
                        FAC_FACULTY_PERMISSIONS) && usrFacRepository.findByFacultyFacIdAndUserUsrId(facId,
                        userProvider.getUserIdFromSession()).isPresent());
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void enforceUsrFacForFaculty(long facId, UserPermAccess facultyPermAccess) {
        if (!validateUsrFacForFaculty(facId, facultyPermAccess)) {
            throw new OutCurrException(OutCurrExceptionType.FACULTY_FORBIDDEN_FAC_ID);
        }
    }

}