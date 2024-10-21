package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssmtGenPlanRepository extends JpaRepository<AssmtGenPlan, Long> {

    List<AssmtGenPlan> findAllByAcadProgramAcpIdAndAsgplaStatus(long acadProgId, String asgplaStatus);

    Optional<AssmtGenPlan> findAllByAsgplaIdAndAsgplaStatus(long acadProgId, String asgplaStatus);


    Optional<AssmtGenPlan> findByAcadProgramAcpIdAndAsgplaId(long acadProgId, long asgplaId);
}
