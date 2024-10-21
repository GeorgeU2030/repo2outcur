package co.edu.icesi.dev.outcome_curr_mgmt.persistence.rubric;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.rubric.RubricCell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricCellRepository extends JpaRepository<RubricCell, Long> {

}
