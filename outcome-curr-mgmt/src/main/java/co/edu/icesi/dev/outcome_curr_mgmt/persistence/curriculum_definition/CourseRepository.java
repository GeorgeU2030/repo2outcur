package co.edu.icesi.dev.outcome_curr_mgmt.persistence.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT acadProgCur.courses "
            + "FROM AcadProgCurriculum acadProgCur "
            + "WHERE acadProgCur.acadProgram.acpId = :acadProgId "
            + "AND acadProgCur.acadProgram.faculty.facId = :facultyId "
            + "AND acadProgCur.apcId = :acadProgCurrId ")
    List<Course> findAllByAcadProgIdAndFacultyIdAndAcadProgCurrId(long acadProgId, long facultyId, long acadProgCurrId);
}