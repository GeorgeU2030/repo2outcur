package co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.PerfLvl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfLvlRepository extends JpaRepository<PerfLvl, Long> {

    Optional<PerfLvl> findByAcadProgramAcpIdAndPlNameSpa(long acadProgramId, String name);

    Optional<PerfLvl> findByAcadProgramAcpIdAndPlNameEng(long acadProgramId, String name);

    List<PerfLvl> findAllByAcadProgramAcpId(long acadProgramId);

    Optional<PerfLvl> findByAcadProgramAcpIdAndPlId(long acadProgId,long perfLvlId);

}

