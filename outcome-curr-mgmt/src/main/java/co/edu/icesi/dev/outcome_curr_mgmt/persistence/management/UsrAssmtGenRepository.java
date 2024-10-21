package co.edu.icesi.dev.outcome_curr_mgmt.persistence.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrAssmtGen;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrAssmtGenPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsrAssmtGenRepository extends JpaRepository<UsrAssmtGen, UsrAssmtGenPK> {

    Optional<UsrAssmtGen> findByAssmtGenPlanAsgplaIdAndUserUsrId(long asgpId, long usrId);
}
