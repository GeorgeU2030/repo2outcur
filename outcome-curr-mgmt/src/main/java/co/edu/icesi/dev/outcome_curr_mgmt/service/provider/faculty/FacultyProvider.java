package co.edu.icesi.dev.outcome_curr_mgmt.service.provider.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.faculty.FacultyInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.faculty.FacultyOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;

public interface FacultyProvider {
    FacultyOutDTO saveFaculty(FacultyInDTO facultyInDTO);
    void addActionToChangelog(ChangeLogAction action, long facId, String affectedTables, Faculty newfaculty,
            Object oldFaculty);
    Faculty findFacultyByFacId(long facId);
    void checkIfEngNameIsAlreadyUsed(String facNameEng);
    void checkIfSpaNameIsAlreadyUsed(String facNameSpa);
    void checkIfExternalIdIsAlreadyImported(String externalId);
    FacultyOutDTO getFacultyByNameInEng(String name);
    FacultyOutDTO getFacultyByNameInSpa(String name);
    void validateAccess(long facultyId, UserPermAccess permAccess);
}
