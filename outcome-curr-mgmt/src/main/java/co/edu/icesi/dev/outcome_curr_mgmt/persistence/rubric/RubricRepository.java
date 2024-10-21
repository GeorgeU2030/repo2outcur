package co.edu.icesi.dev.outcome_curr_mgmt.persistence.rubric;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.rubric.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubricRepository extends JpaRepository<Rubric, Long> {

}
