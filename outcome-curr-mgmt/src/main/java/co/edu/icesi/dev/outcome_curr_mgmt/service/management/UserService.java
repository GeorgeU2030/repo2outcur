package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.LoginInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.LoginOutDTO;

public interface UserService {

    LoginOutDTO login (LoginInDTO loginInDTO);

}
