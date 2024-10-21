package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition.CourseBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseBlockServiceImpl implements CourseBlockService {

    private final CourseBlockRepository courseBlockRepository;

}
