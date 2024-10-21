package co.edu.icesi.dev.outcome_curr_mgmt.mapper.audit;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.audit.ChangeLogOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.audit.Changelog;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ChangeLogMapper {

    @Mapping(target="user", expression="java(changelog.getUser().getUsrName())")
    @Mapping(source="clogTimestamp", target="clogTimestamp", dateFormat = ChangeLogServiceImpl.DATEFORMAT)
    ChangeLogOutDTO fromChangeLog(Changelog changelog);
}
