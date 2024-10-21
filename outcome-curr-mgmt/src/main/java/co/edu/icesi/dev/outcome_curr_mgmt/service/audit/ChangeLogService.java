package co.edu.icesi.dev.outcome_curr_mgmt.service.audit;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.audit.ChangeLogFilterInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.audit.ChangeLogOutDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.audit.Changelog;
import co.edu.icesi.dev.outcome_curr_mgmt.model.enums.ChangeLogAction;

import java.util.List;

public interface ChangeLogService {

    Changelog addChange(ChangeLogAction clogAction,String clogAffectedRecordId,String clogAffectedTable,Object clogLogNewVal,Object clogLogOldVal);
    List<ChangeLogOutDTO> getAllChanges();
    List<ChangeLogOutDTO> getAllChangesByFilter(ChangeLogFilterInDTO changeLogDateFilterInDTO);

}
