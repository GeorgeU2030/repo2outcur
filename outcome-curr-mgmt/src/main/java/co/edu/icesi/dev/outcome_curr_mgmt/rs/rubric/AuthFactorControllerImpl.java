package co.edu.icesi.dev.outcome_curr_mgmt.rs.rubric;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.rubric.AuthFactorController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.rubric.FactorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthFactorControllerImpl implements AuthFactorController {
    private final FactorService factorService;

}
