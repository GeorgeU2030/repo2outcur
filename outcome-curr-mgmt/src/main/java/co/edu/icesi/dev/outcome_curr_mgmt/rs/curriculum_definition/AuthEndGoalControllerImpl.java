package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_definition;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_definition.AuthEndGoalController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition.EndGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthEndGoalControllerImpl implements AuthEndGoalController {
    private final EndGoalService endGoalService;

}
