package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_instace;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_instance.RubricCellFilled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricCellFilledRepository extends JpaRepository<RubricCellFilled, Long> {

}
