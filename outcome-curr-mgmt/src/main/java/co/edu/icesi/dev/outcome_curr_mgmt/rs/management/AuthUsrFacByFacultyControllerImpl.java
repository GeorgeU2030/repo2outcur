package co.edu.icesi.dev.outcome_curr_mgmt.rs.management;

import co.edu.icesi.dev.outcome_curr.mgmt.rs.management.AuthUsrFacByFacultyController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.management.UsrFacService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthUsrFacByFacultyControllerImpl implements AuthUsrFacByFacultyController {
    private final UsrFacService usrFacService;

}
