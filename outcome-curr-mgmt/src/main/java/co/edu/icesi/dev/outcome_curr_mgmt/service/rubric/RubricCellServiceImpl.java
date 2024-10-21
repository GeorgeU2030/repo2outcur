package co.edu.icesi.dev.outcome_curr_mgmt.service.rubric;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.rubric.RubricCellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RubricCellServiceImpl implements RubricCellService {

    private final RubricCellRepository rubricCellRepository;

}
