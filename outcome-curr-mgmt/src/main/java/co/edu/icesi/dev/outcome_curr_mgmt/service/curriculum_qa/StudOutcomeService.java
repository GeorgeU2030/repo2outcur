package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;

import java.util.List;

public interface StudOutcomeService {
    List<StudOutcome> getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(long acadProgCurId,long acadProgId, long facultyId);
}
