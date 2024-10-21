package co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty;

public interface FacultyValidator {
    void enforceUsrFacForFaculty(long facId, UserPermAccess facultyPermAccess);

}