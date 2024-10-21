package co.edu.icesi.dev.outcome_curr_mgmt.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthPiAssessmentLvlController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.AssessmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthPiAssessmentLvlControllerImpl implements AuthPiAssessmentLvlController {
    private final AssessmentTypeService piLvlCategService;


}
