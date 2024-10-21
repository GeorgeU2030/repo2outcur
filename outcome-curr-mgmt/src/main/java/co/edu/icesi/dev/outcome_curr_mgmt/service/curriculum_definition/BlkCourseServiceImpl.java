package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition.CourseBlockCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlkCourseServiceImpl implements BlkCourseService {

    private final CourseBlockCourseRepository blkCourseRepository;

}
