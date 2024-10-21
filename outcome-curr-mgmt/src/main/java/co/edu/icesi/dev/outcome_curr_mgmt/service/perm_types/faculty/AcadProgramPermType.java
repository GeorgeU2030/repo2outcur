package co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.BaseStatus;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.BasePermLevel;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;

import java.util.List;
import java.util.Map;

import static co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthAcadProgramPermissions.*;

public interface AcadProgramPermType {
    enum AcadProgramPermStatus implements BaseStatus {
        FUTURE,
        CURRENT,
        INACTIVE
    }


    //Permission for AcadProgramService related to Faculty
    Map<UserPermAccess, List<String>> ACADPROG_FUTURE_FACTULTIES = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_FUTURE_ACADPROGRAMS_ANY),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_FUTURE_ACADPROGRAMS_ANY, ROLE_ADMIN_FUTURE_ACADPROGRAMS_ANY));
    Map<UserPermAccess, List<String>> ACADPROG_FUTURE_FACULTY = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_FUTURE_ACADPROGRAMS_FAC, ROLE_ADMIN_FUTURE_ACADPROGRAMS_OWN),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_FUTURE_ACADPROGRAMS_FAC, ROLE_QUERY_FUTURE_ACADPROGRAMS_OWN,
                    ROLE_ADMIN_FUTURE_ACADPROGRAMS_FAC, ROLE_ADMIN_FUTURE_ACADPROGRAMS_OWN));

    Map<UserPermAccess, List<String>> ACADPROG_CURRENT_FACTULTIES = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_CURRENT_ACADPROGRAMS_ANY),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_CURRENT_ACADPROGRAMS_ANY, ROLE_ADMIN_CURRENT_ACADPROGRAMS_ANY));
    Map<UserPermAccess, List<String>> ACADPROG_CURRENT_FACULTY = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_CURRENT_ACADPROGRAMS_FAC, ROLE_ADMIN_CURRENT_ACADPROGRAMS_OWN),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_CURRENT_ACADPROGRAMS_FAC, ROLE_QUERY_CURRENT_ACADPROGRAMS_OWN,
                    ROLE_ADMIN_CURRENT_ACADPROGRAMS_FAC, ROLE_ADMIN_CURRENT_ACADPROGRAMS_OWN));

    Map<UserPermAccess, List<String>> ACADPROG_INACTIVE_FACTULTIES = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_INACTIVE_ACADPROGRAMS_ANY),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_INACTIVE_ACADPROGRAMS_ANY, ROLE_ADMIN_INACTIVE_ACADPROGRAMS_ANY));
    Map<UserPermAccess, List<String>> ACADPROG_INACTIVE_FACULTY = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_INACTIVE_ACADPROGRAMS_FAC, ROLE_ADMIN_INACTIVE_ACADPROGRAMS_OWN),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_INACTIVE_ACADPROGRAMS_FAC, ROLE_QUERY_INACTIVE_ACADPROGRAMS_OWN,
                    ROLE_ADMIN_INACTIVE_ACADPROGRAMS_FAC, ROLE_ADMIN_INACTIVE_ACADPROGRAMS_OWN));

    //Permission for AcadProgramService related to AcadProgram
    Map<UserPermAccess, List<String>> ACADPROG_FUTURE_ACADPROGRAMS = Map.of(
            UserPermAccess.ADMIN,
            List.of(ROLE_ADMIN_FUTURE_ACADPROGRAMS_ANY, ROLE_ADMIN_FUTURE_ACADPROGRAMS_FAC),
            UserPermAccess.QUERY,
            List.of(ROLE_QUERY_FUTURE_ACADPROGRAMS_ANY, ROLE_QUERY_FUTURE_ACADPROGRAMS_FAC,
                    ROLE_ADMIN_FUTURE_ACADPROGRAMS_ANY, ROLE_ADMIN_FUTURE_ACADPROGRAMS_FAC));
    Map<UserPermAccess, List<String>> ACADPROG_FUTURE_ACADPROGRAM = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_FUTURE_ACADPROGRAMS_OWN),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_FUTURE_ACADPROGRAMS_OWN, ROLE_ADMIN_FUTURE_ACADPROGRAMS_OWN));

    Map<UserPermAccess, List<String>> ACADPROG_CURRENT_ACADPROGRAMS = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_CURRENT_ACADPROGRAMS_ANY, ROLE_ADMIN_CURRENT_ACADPROGRAMS_FAC),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_CURRENT_ACADPROGRAMS_ANY, ROLE_QUERY_CURRENT_ACADPROGRAMS_FAC,
                    ROLE_ADMIN_CURRENT_ACADPROGRAMS_ANY, ROLE_ADMIN_CURRENT_ACADPROGRAMS_FAC));
    Map<UserPermAccess, List<String>> ACADPROG_CURRENT_ACADPROGRAM = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_CURRENT_ACADPROGRAMS_OWN),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_CURRENT_ACADPROGRAMS_OWN, ROLE_ADMIN_CURRENT_ACADPROGRAMS_OWN));

    Map<UserPermAccess, List<String>> ACADPROG_INACTIVE_ACADPROGRAMS = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_INACTIVE_ACADPROGRAMS_ANY, ROLE_ADMIN_INACTIVE_ACADPROGRAMS_FAC),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_INACTIVE_ACADPROGRAMS_ANY, ROLE_QUERY_INACTIVE_ACADPROGRAMS_FAC,
                    ROLE_ADMIN_INACTIVE_ACADPROGRAMS_ANY, ROLE_ADMIN_INACTIVE_ACADPROGRAMS_FAC));
    Map<UserPermAccess, List<String>> ACADPROG_INACTIVE_ACADPROGRAM = Map.of(
            UserPermAccess.ADMIN, List.of(ROLE_ADMIN_INACTIVE_ACADPROGRAMS_OWN),
            UserPermAccess.QUERY, List.of(ROLE_QUERY_INACTIVE_ACADPROGRAMS_OWN, ROLE_ADMIN_INACTIVE_ACADPROGRAMS_OWN));

    //Permission for AssesmentGenPlanService related to Faculty



    // Mappers aggregated by status for Factulty

    Map<BasePermLevel, Map<UserPermAccess, List<String>>> ACADPROG_FUTURE_FACULTY_PERMISSIONS = Map.of(
            BasePermLevel.ANY,
            ACADPROG_FUTURE_FACTULTIES, BasePermLevel.OWN, ACADPROG_FUTURE_FACULTY);
    Map<BasePermLevel, Map<UserPermAccess, List<String>>> ACADPROG_CURRENT_FACULTY_PERMISSIONS = Map.of(
            BasePermLevel.ANY,
            ACADPROG_CURRENT_FACTULTIES, BasePermLevel.OWN, ACADPROG_CURRENT_FACULTY);
    Map<BasePermLevel, Map<UserPermAccess, List<String>>> ACADPROG_INACTIVE_FACULTY_PERMISSIONS = Map.of(
            BasePermLevel.ANY,
            ACADPROG_INACTIVE_FACTULTIES, BasePermLevel.OWN, ACADPROG_INACTIVE_FACULTY);

    // Mappers aggregated by status for ProgAcad
    Map<BasePermLevel, Map<UserPermAccess, List<String>>> ACADPROG_FUTURE_ACADPROG_PERMISSIONS = Map.of(
            BasePermLevel.ANY,
            ACADPROG_FUTURE_ACADPROGRAMS, BasePermLevel.OWN,
            ACADPROG_FUTURE_ACADPROGRAM);
    Map<BasePermLevel, Map<UserPermAccess, List<String>>> ACADPROG_CURRENT_ACADPROG_PERMISSIONS = Map.of(
            BasePermLevel.ANY,
            ACADPROG_CURRENT_ACADPROGRAMS, BasePermLevel.OWN,
            ACADPROG_CURRENT_ACADPROGRAM);
    Map<BasePermLevel, Map<UserPermAccess, List<String>>> ACADPROG_INACTIVE_ACADPROG_PERMISSIONS = Map.of(
            BasePermLevel.ANY,
            ACADPROG_INACTIVE_ACADPROGRAMS, BasePermLevel.OWN,
            ACADPROG_INACTIVE_ACADPROGRAM);

    //Mappers for use

    Map<BaseStatus, Map<BasePermLevel, Map<UserPermAccess, List<String>>>> ACADPROG_FACULTY_PERMISSIONS = Map.of(
            AcadProgramPermStatus.FUTURE,
            ACADPROG_FUTURE_FACULTY_PERMISSIONS, AcadProgramPermStatus.CURRENT, ACADPROG_CURRENT_FACULTY_PERMISSIONS,
            AcadProgramPermStatus.INACTIVE, ACADPROG_INACTIVE_FACULTY_PERMISSIONS);
    Map<BaseStatus, Map<BasePermLevel, Map<UserPermAccess, List<String>>>>
            ACADPROG_ACADPROG_PERMISSIONS = Map.of(AcadProgramPermStatus.FUTURE,
            ACADPROG_FUTURE_ACADPROG_PERMISSIONS, AcadProgramPermStatus.CURRENT,
            ACADPROG_CURRENT_ACADPROG_PERMISSIONS, AcadProgramPermStatus.INACTIVE,
            ACADPROG_INACTIVE_ACADPROG_PERMISSIONS);

}