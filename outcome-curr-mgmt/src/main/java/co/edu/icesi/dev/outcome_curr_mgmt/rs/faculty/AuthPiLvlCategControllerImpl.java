package co.edu.icesi.dev.outcome_curr_mgmt.rs.faculty;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.faculty.AuthPiLvlCategController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.faculty.PiLvlCategService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthPiLvlCategControllerImpl implements AuthPiLvlCategController {
    private final PiLvlCategService piLvlCategService;

}
