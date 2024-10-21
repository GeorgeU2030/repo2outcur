package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.AcadProgCurriculum;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition.AcadProgCurriculumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcadProgCurriculumServiceImpl implements AcadProgCurriculumService {

    private final AcadProgCurriculumRepository acadProgCurriculumRepository;

    @Override
    public AcadProgCurriculum findAcadProgCurriculumById(long id) {
        return acadProgCurriculumRepository.findById(id).orElseThrow(() -> new RuntimeException("AcadProgCurriculum not found"));
    }
}
