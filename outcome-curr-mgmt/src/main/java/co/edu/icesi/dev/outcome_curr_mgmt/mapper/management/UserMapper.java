package co.edu.icesi.dev.outcome_curr_mgmt.mapper.management;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.management.LoginOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User fromLoginOutDTO(LoginOutDTO loginOutDTO);

}
