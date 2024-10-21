package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_instance;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_instance.AuthRubricCellByOfferedCourseController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_instance.OfferedCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRubricCellByOfferedCourseControllerImpl implements AuthRubricCellByOfferedCourseController {
    private final OfferedCourseService offeredCourseService;

}
