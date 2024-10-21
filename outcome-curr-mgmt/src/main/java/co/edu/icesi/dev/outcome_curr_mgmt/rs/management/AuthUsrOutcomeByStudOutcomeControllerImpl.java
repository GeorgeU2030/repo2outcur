package co.edu.icesi.dev.outcome_curr_mgmt.rs.management;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.management.AuthUsrOutcomeByStudOutcomeController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.management.UsrOutComeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthUsrOutcomeByStudOutcomeControllerImpl implements AuthUsrOutcomeByStudOutcomeController {
    private final UsrOutComeService usrOutComeService;


}
