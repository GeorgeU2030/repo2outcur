package co.edu.icesi.dev.outcome_curr_mgmt.persistence.management;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcPeriodRepository extends JpaRepository<AcPeriod, Long> {

    Optional<AcPeriod> findByAcPeriodNameSpa(@Param("acaPeriodSpaName") String acaPeriodSpaName);

    Optional<AcPeriod> findByAcPeriodNumeric(@Param("acaPeriodNumeric") int acaPeriodNumeric);
}
