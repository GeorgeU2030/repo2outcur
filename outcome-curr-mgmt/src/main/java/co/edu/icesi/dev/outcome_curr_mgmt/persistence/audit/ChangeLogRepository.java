package co.edu.icesi.dev.outcome_curr_mgmt.persistence.audit;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.audit.Changelog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChangeLogRepository extends JpaRepository<Changelog, Long> {

    @Query("SELECT c FROM Changelog c WHERE"
            + " (:usrName IS NULL OR c.user IN (SELECT u FROM User u WHERE u.usrName LIKE %:usrName%))"
            + " AND (:clogAffectedTable IS NULL OR c.clogAffectedTable = :clogAffectedTable)"
            + " AND ((:clogTimestampStart IS NULL AND :clogTimestampEnd IS NULL) OR c.clogTimestamp BETWEEN :clogTimestampStart AND :clogTimestampEnd)")
    List<Changelog> findAllByFilter(@Param("usrName") String usrName, @Param("clogAffectedTable") String clogAffectedTable, @Param("clogTimestampStart") Date clogTimestampStart, @Param("clogTimestampEnd") Date clogTimestampEnd);
}


