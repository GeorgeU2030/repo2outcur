package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudOutcomeRepository extends JpaRepository<StudOutcome, Long> {

    @Query("SELECT acadProgCur.studOutcomes "
            + "FROM AcadProgCurriculum acadProgCur "
            + "WHERE acadProgCur.apcId = :acadProgCurrId "
            + "AND acadProgCur.acadProgram.faculty.facId = :facultyId "
            + "AND acadProgCur.acadProgram.acpId = :acadProgId")
    List<StudOutcome> getAllStudOutcomesByAcadProgCurrIdAndAcadProgIdAndFacultyId(long acadProgCurrId,long acadProgId, long facultyId);
}
