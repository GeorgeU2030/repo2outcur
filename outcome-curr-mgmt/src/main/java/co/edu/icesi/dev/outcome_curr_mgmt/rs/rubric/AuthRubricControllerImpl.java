package co.edu.icesi.dev.outcome_curr_mgmt.rs.rubric;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.rubric.AuthRubricController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.rubric.RubricService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRubricControllerImpl implements AuthRubricController {
    private final RubricService rubricService;

}
