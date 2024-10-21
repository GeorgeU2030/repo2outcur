package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.MatrixDTO;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.CurrMap;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;

import java.util.List;

public interface CurrMapService {
    List<CurrMap> getAllCurrMapByAcadProgCurrIdAndPerfIndIdAndCourseId(long acadProgCurrId, long perfIndId,
            long courseId, long acadProgId);

    MatrixDTO getMatrixDTO(long acadProgCurrId, long acadProgId,
            List<StudOutcome> studOutcomes, List<Course> courses);

    void updateSuggestedCurrMapRequestStatus(long facultyId, long programId, long acadProgCurrId, long prevCurrMapId, long successorCurrMapId, String destinationState);
}
