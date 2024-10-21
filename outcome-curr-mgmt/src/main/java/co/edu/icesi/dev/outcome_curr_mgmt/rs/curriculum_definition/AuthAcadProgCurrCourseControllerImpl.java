package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_definition;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_definition.AuthAcadProgCurrCourseController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition.AcadProgCurrCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthAcadProgCurrCourseControllerImpl implements AuthAcadProgCurrCourseController {
    private final AcadProgCurrCourseService acadProgCurrCourseService;

}
