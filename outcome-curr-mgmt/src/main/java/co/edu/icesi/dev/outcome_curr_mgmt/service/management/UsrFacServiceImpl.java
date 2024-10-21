package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrFacRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsrFacServiceImpl implements UsrFacService {

    private final UsrFacRepository usrFacRepository;

}
