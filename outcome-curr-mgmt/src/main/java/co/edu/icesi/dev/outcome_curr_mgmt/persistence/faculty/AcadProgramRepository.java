package co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcadProgramRepository extends JpaRepository<AcadProgram, Long> {
    List<AcadProgram> findAllByFacultyFacId(long facultyId);

    Optional<AcadProgram> findByFacultyFacIdAndAcpId(long facultyId, long acpId);

    Optional<AcadProgram> findByAcpId(Long acpId);

}
