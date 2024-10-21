package co.edu.icesi.dev.outcome_curr_mgmt.service.validator;

import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.BaseStatus;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import co.edu.icesi.dev.outcome_curr_mgmt.service.perm_types.BasePermLevel;
import co.edu.icesi.dev.outcome_curr_mgmt.service.validator.faculty.UserPermAccess;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public abstract class BaseValidator {
    protected SaamfiJwtTools saamfiJwtTools;

    protected boolean validatePermissionsWithStatus(UserPermAccess facultyPermAccess, BaseStatus acadProgramPermStatus,
            BasePermLevel basePermLevel,
            Map<BaseStatus, Map<BasePermLevel, Map<UserPermAccess, List<String>>>> permissions) {
        return saamfiJwtTools.loggedInUserHasPermission(
                permissions.get(acadProgramPermStatus).get(basePermLevel).get(facultyPermAccess));
    }

    protected boolean validatePermissionsWithoutStatus(UserPermAccess facultyPermAccess, BasePermLevel basePermLevel,
            Map<BasePermLevel, Map<UserPermAccess, List<String>>> permissions) {
        return saamfiJwtTools.loggedInUserHasPermission(
                permissions.get(basePermLevel).get(facultyPermAccess));
    }
}
