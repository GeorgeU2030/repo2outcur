package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrOutcomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsrOutComeServiceImpl implements UsrOutComeService {

    private final UsrOutcomeRepository usrOutcomeRepository;

}
