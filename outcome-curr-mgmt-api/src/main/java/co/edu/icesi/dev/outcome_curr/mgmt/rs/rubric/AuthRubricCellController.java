package co.edu.icesi.dev.outcome_curr.mgmt.rs.rubric;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/auth/faculties/{facultyId}/acad_programs/{acadprogramId}/assessemnt_plan/{asgplaId}/"
        + "student_outcomes/{studoutId}/perf_indicators/{perfindId}/factors/{factorId}/rubrics/{rubricId}/rubric_cells")
public interface AuthRubricCellController {

}
