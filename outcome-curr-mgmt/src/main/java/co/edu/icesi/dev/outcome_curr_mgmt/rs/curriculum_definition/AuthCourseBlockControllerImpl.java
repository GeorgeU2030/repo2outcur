package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_definition;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_definition.AuthCourseBlockController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.PiLvlCategService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthCourseBlockControllerImpl implements AuthCourseBlockController {
    private final PiLvlCategService piLvlCategService;

}
