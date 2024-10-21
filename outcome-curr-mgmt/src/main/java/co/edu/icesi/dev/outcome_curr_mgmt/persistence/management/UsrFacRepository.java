package co.edu.icesi.dev.outcome_curr_mgmt.persistence.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrFac;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrFacPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsrFacRepository extends JpaRepository<UsrFac, UsrFacPK> {

    Optional<UsrFac> findByFacultyFacIdAndUserUsrId(long facId, long usrId);
}
