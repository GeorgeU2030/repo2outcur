package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa.AuthAssmtPlanOutController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa.AssmtPlanOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthAssmtPlanOutControllerImpl implements AuthAssmtPlanOutController {
    private final AssmtPlanOutService assmtPlanService;

}
