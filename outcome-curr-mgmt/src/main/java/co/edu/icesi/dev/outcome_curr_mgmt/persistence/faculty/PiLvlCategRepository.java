package co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PiLvlCateg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiLvlCategRepository extends JpaRepository<PiLvlCateg, Long> {

}
