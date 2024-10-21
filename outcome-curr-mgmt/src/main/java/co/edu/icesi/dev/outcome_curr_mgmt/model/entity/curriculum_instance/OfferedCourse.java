package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_instance;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrOffcourse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Entity
@DataModelerGenerated
@Builder
@AllArgsConstructor
@Table(name = "OFFERED_COURSE")
@NamedQuery(name = "OfferedCourse.findAll", query = "SELECT o FROM OfferedCourse o")
public class OfferedCourse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "OFFERED_COURSE_OFCID_GENERATOR", allocationSize = 1, sequenceName = "OFFERED_COURSE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFFERED_COURSE_OFCID_GENERATOR")
    @Column(name = "OFC_ID")
    private long ofcId;

    @Column(name = "OFC_GROUP_NUMBER")
    private int ofcGroupNumber;

    @Column(name = "OFC_IS_ACTIVE")
    private char ofcIsActive;

    @Column(name = "OFC_NRC")
    private int ofcNrc;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "AP_AC_PERIOD_ID")
    private AcPeriod acPeriod;

    //bi-directional many-to-one association to Course
    @ManyToOne
    @JoinColumn(name = "CRS_COURSE_ID")
    private Course course;

    //bi-directional many-to-one association to RubricCellFilled
    @OneToMany(mappedBy = "offeredCourse")
    private List<RubricCellFilled> rubricCellFilleds;

    //bi-directional many-to-one association to UsrOffcourse
    @OneToMany(mappedBy = "offeredCourse")
    private List<UsrOffcourse> usrOffcourses;

    public OfferedCourse() {
        //Entity constructor
    }

    public long getOfcId() {
        return this.ofcId;
    }

    public void setOfcId(long ofcId) {
        this.ofcId = ofcId;
    }

    public int getOfcGroupNumber() {
        return this.ofcGroupNumber;
    }

    public void setOfcGroupNumber(int ofcGroupNumber) {
        this.ofcGroupNumber = ofcGroupNumber;
    }

    public char getOfcIsActive() {
        return this.ofcIsActive;
    }

    public void setOfcIsActive(char ofcIsActive) {
        this.ofcIsActive = ofcIsActive;
    }

    public int getOfcNrc() {
        return this.ofcNrc;
    }

    public void setOfcNrc(int ofcNrc) {
        this.ofcNrc = ofcNrc;
    }

    public AcPeriod getAcPeriod() {
        return this.acPeriod;
    }

    public void setAcPeriod(AcPeriod acPeriod) {
        this.acPeriod = acPeriod;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<RubricCellFilled> getRubricCellFilleds() {
        return this.rubricCellFilleds;
    }

    public void setRubricCellFilleds(List<RubricCellFilled> rubricCellFilleds) {
        this.rubricCellFilleds = rubricCellFilleds;
    }

    public RubricCellFilled addRubricCellFilled(RubricCellFilled rubricCellFilled) {
        getRubricCellFilleds().add(rubricCellFilled);
        rubricCellFilled.setOfferedCourse(this);

        return rubricCellFilled;
    }

    public RubricCellFilled removeRubricCellFilled(RubricCellFilled rubricCellFilled) {
        getRubricCellFilleds().remove(rubricCellFilled);
        rubricCellFilled.setOfferedCourse(null);

        return rubricCellFilled;
    }

    public List<UsrOffcourse> getUsrOffcourses() {
        return this.usrOffcourses;
    }

    public void setUsrOffcourses(List<UsrOffcourse> usrOffcourses) {
        this.usrOffcourses = usrOffcourses;
    }

    public UsrOffcourse addUsrOffcours(UsrOffcourse usrOffcours) {
        getUsrOffcourses().add(usrOffcours);
        usrOffcours.setOfferedCourse(this);

        return usrOffcours;
    }

    public UsrOffcourse removeUsrOffcours(UsrOffcourse usrOffcours) {
        getUsrOffcourses().remove(usrOffcours);
        usrOffcours.setOfferedCourse(null);

        return usrOffcours;
    }

}