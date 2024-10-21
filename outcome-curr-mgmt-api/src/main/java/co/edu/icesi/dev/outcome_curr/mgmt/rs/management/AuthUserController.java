package co.edu.icesi.dev.outcome_curr.mgmt.rs.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.LoginInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.LoginOutDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="UserWebService")
@RestController
@RequestMapping(value = "/v1/auth/users")
public interface AuthUserController {
    @PostMapping(value = "/login")
    LoginOutDTO login(@Valid @RequestBody LoginInDTO loginDTO);

}
