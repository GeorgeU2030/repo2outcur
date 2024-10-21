package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.PerfindEndgoal;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.PerfindEndgoalPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfIndEndGoalRepository extends JpaRepository<PerfindEndgoal, PerfindEndgoalPK> {

}
