package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.AcadProgCurriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcadProgCurriculumRepository extends JpaRepository<AcadProgCurriculum, Long> {

}
