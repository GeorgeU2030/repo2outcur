package co.edu.icesi.dev.outcome_curr_mgmt.persistence.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrOffcourse;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrOffcoursePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsrOffCourseRepository extends JpaRepository<UsrOffcourse, UsrOffcoursePK> {

}
