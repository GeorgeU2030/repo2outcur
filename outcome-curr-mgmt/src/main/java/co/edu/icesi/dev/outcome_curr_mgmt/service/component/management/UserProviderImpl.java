package co.edu.icesi.dev.outcome_curr_mgmt.service.component.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UserRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProviderImpl implements UserProvider{

    private final UserRepository userRepository;
    private final SaamfiJwtTools saamfiJwtTools;

    @Override
    public User getUserFromSession()
    {
        return userRepository.findByUsrName(saamfiJwtTools.getLoggedInUserUsername());

    }

    @Override
    public long getUserIdFromSession()
    {
        return userRepository.findByUsrName(saamfiJwtTools.getLoggedInUserUsername()).getUsrId();

    }
}
