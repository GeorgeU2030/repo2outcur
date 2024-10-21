package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition.EndGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EndGoalServiceImpl implements EndGoalService {

    private final EndGoalRepository endGoalRepository;

}
