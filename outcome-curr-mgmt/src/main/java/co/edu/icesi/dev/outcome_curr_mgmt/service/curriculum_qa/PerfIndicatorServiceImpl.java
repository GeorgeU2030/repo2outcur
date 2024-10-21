package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.PerfIndicatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfIndicatorServiceImpl implements PerfIndicatorService {

    private final PerfIndicatorRepository perfIndicatorRepository;

}
