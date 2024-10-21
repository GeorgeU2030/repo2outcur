package co.edu.icesi.dev.outcome_curr_mgmt.persistence.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrBlock;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrBlockPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsrBlockRepository extends JpaRepository<UsrBlock, UsrBlockPK> {

}
