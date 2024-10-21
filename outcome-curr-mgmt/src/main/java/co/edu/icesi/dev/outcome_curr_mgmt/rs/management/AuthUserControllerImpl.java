package co.edu.icesi.dev.outcome_curr_mgmt.rs.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.LoginInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.LoginOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.management.AuthUserController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.management.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthUserControllerImpl implements AuthUserController {
    private final UserService userService;

    @Override
    public LoginOutDTO login(LoginInDTO loginDTO) {
        return userService.login(loginDTO);
    }
}
