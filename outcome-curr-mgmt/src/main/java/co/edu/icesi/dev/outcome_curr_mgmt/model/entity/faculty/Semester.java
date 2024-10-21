package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.Cell;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@NamedQuery(name = "Semester.findAll", query = "SELECT s FROM Semester s")
public class Semester extends Cell implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "SEMESTER_SEMID_GENERATOR", allocationSize = 1, sequenceName = "SEMESTER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEMESTER_SEMID_GENERATOR")
    @Column(name = "SEM_ID")
    private long semId;

    @Column(name = "SEM_NAME")
    private String semName;

    //bi-directional many-to-one association to Course
    @OneToMany(mappedBy = "semester")
    private List<Course> courses;

    public Semester() {
        //Entity constructor
    }

    @Override
    public void initializeCellValues() {
        putKeyValueInMap("semester", semName);
    }

    public long getSemId() {
        return this.semId;
    }

    public void setSemId(long semId) {
        this.semId = semId;
    }

    public String getSemName() {
        return this.semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
        putKeyValueInMap("semester", semName);
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course addCours(Course cours) {
        getCourses().add(cours);
        cours.setSemester(this);

        return cours;
    }

    public Course removeCours(Course cours) {
        getCourses().remove(cours);
        cours.setSemester(null);

        return cours;
    }

}