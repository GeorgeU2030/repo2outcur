package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsrBlockServiceImpl implements UsrBlockService {

    private final UsrBlockRepository usrBlockRepository;

}
