package co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AssessmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentTypeRepository extends JpaRepository<AssessmentType, Long> {

}
