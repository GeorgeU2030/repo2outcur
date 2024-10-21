package co.edu.icesi.dev.outcome_curr_mgmt.persistence.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrPrg;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrPrgPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsrPrgRepository extends JpaRepository<UsrPrg, UsrPrgPK> {

    Optional<UsrPrg> findByAcadProgramAcpIdAndUserUsrId(long acpId, long usrId);
}
