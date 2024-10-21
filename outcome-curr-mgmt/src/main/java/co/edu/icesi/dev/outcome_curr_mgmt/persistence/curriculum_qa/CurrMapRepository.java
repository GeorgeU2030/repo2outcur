package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.CurrMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrMapRepository extends JpaRepository<CurrMap, Long> {
    List<CurrMap> getAllByAcadProgCurriculumApcIdAndPerfIndicatorPiIdAndCourseCourseIdAndAcadProgCurriculumAcadProgramAcpIdOrderByCmAcceptedDateDesc(
            long acadProgCurrId, long perfIndId, long courseId, long acadProgId);
}
