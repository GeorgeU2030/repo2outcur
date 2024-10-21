package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtPlanSubcyclev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssmtPlanSubCycleRepository extends JpaRepository<AssmtPlanSubcyclev, Long> {

}
