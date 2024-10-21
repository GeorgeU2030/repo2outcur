package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty.AcadProgramPermType;

public interface AcadProgramValidator {
    void enforceUsrPrgForAcadProgram(long acadProgId, UserPermAccess facultyPermAccess,
            AcadProgramPermType.AcadProgramPermStatus acadProgramPermStatus);

    void enforceUsrFacForAcadProgram(long facId, UserPermAccess facultyPermAccess,
            AcadProgramPermType.AcadProgramPermStatus acadProgramPermStatus);

    AcadProgram validatAcadProgOnFaculty(long facId, long acadProgId);
}
