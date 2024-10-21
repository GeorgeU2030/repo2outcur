package co.edu.icesi.dev.outcome_curr_mgmt.rs.rubric;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.rubric.AuthRubricCellController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.rubric.RubricCellService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRubricCellControllerImpl implements AuthRubricCellController {
    private final RubricCellService rubricCellService;


}
