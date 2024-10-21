package co.edu.icesi.dev.outcome_curr_mgmt.persistence.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByFacNameEng(String facNameEng);

    Optional<Faculty> findByFacNameSpa(String facNameSpa);

    Optional<Faculty> findByExternalId(String externalId);
}