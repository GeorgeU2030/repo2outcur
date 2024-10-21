package co.edu.icesi.dev.outcome_curr_mgmt.rs.audit;

import co.edu.icesi.dev.outcome_curr.mgmt.model.stdindto.audit.ChangeLogFilterInDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.model.stdoutdto.audit.ChangeLogOutDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.audit.AuthChangeLogController;
import co.edu.icesi.dev.outcome_curr_mgmt.service.audit.ChangeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class AuthChangeLogControllerImpl implements AuthChangeLogController {
    private final ChangeLogService changeLogService;
    @Override
    public List<ChangeLogOutDTO> getAllChanges() {
        return changeLogService.getAllChanges();
    }

    @Override
    public List<ChangeLogOutDTO> getAllChangesByFilter(ChangeLogFilterInDTO changeLogDateFilterInDTO) {
        return changeLogService.getAllChangesByFilter(changeLogDateFilterInDTO);
    }
}
