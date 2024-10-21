package co.edu.icesi.dev.outcome_curr_mgmt.service.component.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;

public interface UserProvider {
    User getUserFromSession();

    long getUserIdFromSession();
}
