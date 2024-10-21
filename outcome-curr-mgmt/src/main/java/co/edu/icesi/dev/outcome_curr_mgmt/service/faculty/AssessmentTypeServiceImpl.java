package co.edu.icesi.dev.outcome_curr_mgmt.service.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty.AssessmentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssessmentTypeServiceImpl implements AssessmentTypeService {

    private final AssessmentTypeRepository piAssessmentLvlRepository;
    
}
