package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.management.LoginInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.LoginOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrException;
import co.edu.icesi.dev.outcome_curr_mgmt.exception.OutCurrExceptionType;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UserRepository;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.delegate.SaamfiClient;
import co.edu.icesi.dev.outcome_curr_mgmt.saamfi.util.SaamfiJwtTools;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    //TODO add test coverage
    private final UserRepository userRepository;
    private final SaamfiClient saamfiClient;
    private final SaamfiJwtTools saamfiJwtTools;

    @Transactional
    @Override
    public LoginOutDTO login(LoginInDTO loginInDTO) {
        LoginOutDTO loginOutDTO = saamfiClient.getUserLogin(loginInDTO);

        if (!saamfiJwtTools.tokenHasPermission(loginOutDTO.accessToken(), "ROLE_Access-system")){
            throw new OutCurrException(OutCurrExceptionType.USER_INVALID_PERMISSIONS);
        }

        if (userRepository.findById(loginOutDTO.userId()).isEmpty()){
            User userEntity = User.builder()
                    .usrId(loginOutDTO.userId())
                    .usrName(loginOutDTO.userName() + " " + loginOutDTO.userLastname())
                    .usrEmail(loginOutDTO.userEmail())
                    .usrIsActive('Y')
                    .build();
            userRepository.save(userEntity);
        }

        return loginOutDTO;
    }

}
