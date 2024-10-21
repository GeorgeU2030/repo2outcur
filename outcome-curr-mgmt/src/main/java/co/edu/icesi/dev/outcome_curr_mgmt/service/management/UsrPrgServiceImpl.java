package co.edu.icesi.dev.outcome_curr_mgmt.service.management;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.management.UsrPrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsrPrgServiceImpl implements UsrPrgService {

    private final UsrPrgRepository usrPrgRepository;

}
