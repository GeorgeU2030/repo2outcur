package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_definition;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_definition.AuthCourseCurrController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition.CourseCurrService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthCourseCurrControllerImpl implements AuthCourseCurrController {
    private final CourseCurrService courseCurrService;

}
