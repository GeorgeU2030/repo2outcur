package co.edu.icesi.dev.outcome_curr_mgmt.service.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa.StudOutcomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudOutcomeServiceImpl implements StudOutcomeService {

    private final StudOutcomeRepository studOutcomeRepository;

    @Override
    public List<StudOutcome> getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(long acadProgCurrId,long acadProgId, long facultyId) {
        return studOutcomeRepository.getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(acadProgCurrId, acadProgId, facultyId);
    }
}
