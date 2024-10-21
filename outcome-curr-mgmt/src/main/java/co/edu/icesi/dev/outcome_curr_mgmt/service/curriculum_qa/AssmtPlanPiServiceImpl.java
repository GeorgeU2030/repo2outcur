package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.AssmtPlanPiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssmtPlanPiServiceImpl implements AssmtPlanPiService {

    private final AssmtPlanPiRepository assmtPlanPiRepository;

}
