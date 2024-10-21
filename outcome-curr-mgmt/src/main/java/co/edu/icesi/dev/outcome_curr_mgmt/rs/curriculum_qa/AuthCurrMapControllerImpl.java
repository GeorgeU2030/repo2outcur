package co.edu.icesi.dev.outcome_curr_mgmt.rs.curriculum_qa;

import co.edu.icesi.dev.outcome_curr.mgmt.model.curriculum_qa.MatrixDTO;
import co.edu.icesi.dev.outcome_curr.mgmt.rs.curriculum_qa.AuthCurrMapController;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_definition.CourseService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa.CurrMapService;
import co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa.StudOutcomeService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthCurrMapControllerImpl implements AuthCurrMapController {
    private final StudOutcomeService studOutcomeService;
    private final CourseService courseService;
    private final CurrMapService currMapService;

    @Override
    public MatrixDTO getMatrix(long facultyId, long acadProgId, long acadProgCurrId) {
        List<StudOutcome> studOutcomes = studOutcomeService.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(
                acadProgCurrId,
                acadProgId, facultyId);
        List<Course> courses = courseService.findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(acadProgId, facultyId, acadProgCurrId);
        return currMapService.getMatrixDTO(acadProgCurrId, acadProgId, studOutcomes, courses);
    }

    @Override
    public void updateSuggestedCurrMapRequestStatus(long facultyId, long programId, long acadProgCurrId, long prevCurrMapId, long successorCurrMapId, String destinationState) {
        currMapService.updateSuggestedCurrMapRequestStatus(facultyId, programId, acadProgCurrId, prevCurrMapId, successorCurrMapId, destinationState);
    }
}
