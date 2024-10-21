package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_instance;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_instace.RubricCellFilledRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RubricCellFilledServiceImpl implements RubricCellFilledService {

    private final RubricCellFilledRepository rubricCellFilledRepository;

}
