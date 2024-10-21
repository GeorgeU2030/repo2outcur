package co.edu.icesi.dev.outcome_curr_mgmt.rs.management;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.management.AuthUsrBlockByAcadBlockController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition.CourseBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthUsrBlockByAcadBlockControllerImpl implements AuthUsrBlockByAcadBlockController {
    private final CourseBlockService courseBlockService;

}
