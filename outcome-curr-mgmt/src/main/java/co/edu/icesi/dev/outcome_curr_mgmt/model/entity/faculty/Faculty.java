package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrFac;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@NamedQuery(name = "Faculty.findAll", query = "SELECT f FROM Faculty f")
public class Faculty implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "FACULTY_FACID_GENERATOR", allocationSize = 1, sequenceName = "FACULTY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACULTY_FACID_GENERATOR")
    @Column(name = "FAC_ID")
    private long facId;

    @Column(name = "FAC_IS_ACTIVE")
    private char facIsActive;

    @Column(name = "FAC_NAME_ENG")
    private String facNameEng;

    @Column(name = "FAC_NAME_SPA")
    private String facNameSpa;

    @Column(name = "EXTERNAL_ID")
    private String externalId;

    //bi-directional many-to-one association to AcadProgram
    @OneToMany(mappedBy = "faculty")
    private List<AcadProgram> acadPrograms;

    //bi-directional many-to-one association to Course
    @OneToMany(mappedBy = "faculty")
    private List<Course> courses;

    //bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "faculties")
    private List<User> users;

    //bi-directional many-to-one association to UsrFac
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE)
    private List<UsrFac> usrFacs;

    public Faculty() {
        //Entity constructor
    }

    public long getFacId() {
        return this.facId;
    }

    public void setFacId(long facId) {
        this.facId = facId;
    }

    public char getFacIsActive() {
        return this.facIsActive;
    }

    public void setFacIsActive(char facIsActive) {
        this.facIsActive = facIsActive;
    }

    public String getFacNameEng() {
        return this.facNameEng;
    }

    public void setFacNameEng(String facNameEng) {
        this.facNameEng = facNameEng;
    }

    public String getFacNameSpa() {
        return this.facNameSpa;
    }

    public void setFacNameSpa(String facNameSpa) {
        this.facNameSpa = facNameSpa;
    }

    public List<AcadProgram> getAcadPrograms() {
        return this.acadPrograms;
    }

    public void setAcadPrograms(List<AcadProgram> acadPrograms) {
        this.acadPrograms = acadPrograms;
    }

    public AcadProgram addAcadProgram(AcadProgram acadProgram) {
        getAcadPrograms().add(acadProgram);
        acadProgram.setFaculty(this);

        return acadProgram;
    }

    public AcadProgram removeAcadProgram(AcadProgram acadProgram) {
        getAcadPrograms().remove(acadProgram);
        acadProgram.setFaculty(null);

        return acadProgram;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course addCours(Course cours) {
        getCourses().add(cours);
        cours.setFaculty(this);

        return cours;
    }

    public Course removeCours(Course cours) {
        getCourses().remove(cours);
        cours.setFaculty(null);

        return cours;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<UsrFac> getUsrFacs() {
        return this.usrFacs;
    }

    public void setUsrFacs(List<UsrFac> usrFacs) {
        this.usrFacs = usrFacs;
    }

    public UsrFac addUsrFac(UsrFac usrFac) {
        getUsrFacs().add(usrFac);
        usrFac.setFaculty(this);

        return usrFac;
    }

    public UsrFac removeUsrFac(UsrFac usrFac) {
        getUsrFacs().remove(usrFac);
        usrFac.setFaculty(null);

        return usrFac;
    }

}