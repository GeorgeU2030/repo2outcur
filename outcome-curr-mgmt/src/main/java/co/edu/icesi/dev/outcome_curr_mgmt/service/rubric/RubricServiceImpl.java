package co.edu.icesi.dev.outcome_curr_mgmt.service.rubric;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.rubric.RubricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RubricServiceImpl implements RubricService {

    private final RubricRepository rubricRepository;

}
