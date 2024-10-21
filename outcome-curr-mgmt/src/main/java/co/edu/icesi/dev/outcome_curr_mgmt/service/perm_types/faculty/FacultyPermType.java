package co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.BasePermLevel;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;

import java.util.List;
import java.util.Map;

import static co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthFacultyPermissions.*;

public interface FacultyPermType {


    //Permission for FacultyService related to Faculty
    Map<UserPermAccess, List<String>> FAC_FACULTIES = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_FACULTIES_ANY),
            UserPermAccess.QUERY, List.of(ROLE_ADMIN_FACULTIES_ANY, ROLE_QUERY_FACULTIES_ANY));
    Map<UserPermAccess, List<String>> FAC_FACULTY = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_FACULTIES_OWN),
            UserPermAccess.QUERY, List.of(ROLE_ADMIN_FACULTIES_OWN,ROLE_QUERY_FACULTIES_OWN));

    //Permission for AssesmentGenPlanService related to Faculty

    //Mappers for use
    Map<BasePermLevel, Map<UserPermAccess, List<String>>> FAC_FACULTY_PERMISSIONS =
            Map.of(BasePermLevel.ANY,
            FAC_FACULTIES,
                    BasePermLevel.OWN, FAC_FACULTY);

}