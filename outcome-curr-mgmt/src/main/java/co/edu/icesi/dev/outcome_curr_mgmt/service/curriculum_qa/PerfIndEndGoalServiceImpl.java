package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.PerfIndEndGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfIndEndGoalServiceImpl implements PerfIndEndGoalService {

    private final PerfIndEndGoalRepository perfIndEndGoalRepository;

}
