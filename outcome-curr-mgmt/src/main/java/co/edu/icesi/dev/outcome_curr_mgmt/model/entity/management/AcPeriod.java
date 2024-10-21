package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.AcadProgCurriculum;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.CourseBlock;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_instance.OfferedCourse;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtPlanCycle;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtPlanOut;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtPlanSubcyclev;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.rubric.RubricCell;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "AC_PERIOD")
@NamedQuery(name = "AcPeriod.findAll", query = "SELECT a FROM AcPeriod a")
public class AcPeriod implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "AC_PERIOD_ACPERIODID_GENERATOR", allocationSize = 1, sequenceName = "AC_PERIOD_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AC_PERIOD_ACPERIODID_GENERATOR")
    @Column(name = "AC_PERIOD_ID")
    private long acPeriodId;

    @Column(name = "AC_PERIOD_NAME_ENG")
    private String acPeriodNameEng;

    @Column(name = "AC_PERIOD_NAME_SPA")
    private String acPeriodNameSpa;

    @Column(name = "AC_PERIOD_NUMERIC")
    private int acPeriodNumeric;

    //bi-directional many-to-one association to AcadProgram
    @OneToMany(mappedBy = "endAcPeriod")
    private List<AcadProgram> acadPrograms1;

    //bi-directional many-to-one association to AcadProgram
    @OneToMany(mappedBy = "startAcPeriod")
    private List<AcadProgram> acadPrograms2;

    //bi-directional many-to-one association to AcadProgCurriculum
    @OneToMany(mappedBy = "startAcPeriod")
    private List<AcadProgCurriculum> acadProgCurriculums1;

    //bi-directional many-to-one association to AcadProgCurriculum
    @OneToMany(mappedBy = "endAcPeriod")
    private List<AcadProgCurriculum> acadProgCurriculums2;

    //bi-directional many-to-one association to AssmtGenPlan
    @OneToMany(mappedBy = "startAcPeriod")
    private List<AssmtGenPlan> assmtGenPlans1;

    //bi-directional many-to-one association to AssmtGenPlan
    @OneToMany(mappedBy = "endAcPeriod")
    private List<AssmtGenPlan> assmtGenPlans2;

    //bi-directional many-to-one association to AssmtPlanCycle
    @OneToMany(mappedBy = "endAcPeriod")
    private List<AssmtPlanCycle> assmtPlanCycles1;

    //bi-directional many-to-one association to AssmtPlanCycle
    @OneToMany(mappedBy = "startAcPeriod")
    private List<AssmtPlanCycle> assmtPlanCycles2;

    //bi-directional many-to-one association to AssmtPlanOut
    @OneToMany(mappedBy = "collectAcPeriod")
    private List<AssmtPlanOut> assmtPlanOuts1;

    //bi-directional many-to-one association to AssmtPlanOut
    @OneToMany(mappedBy = "assessAcPeriod")
    private List<AssmtPlanOut> assmtPlanOuts2;

    //bi-directional many-to-one association to AssmtPlanSubcyclev
    @OneToMany(mappedBy = "acPeriod")
    private List<AssmtPlanSubcyclev> assmtPlanSubcyclevs;

    //bi-directional many-to-one association to Course
    @OneToMany(mappedBy = "endAcPeriod")
    private List<Course> courses1;

    //bi-directional many-to-one association to Course
    @OneToMany(mappedBy = "startAcPeriod")
    private List<Course> courses2;

    //bi-directional many-to-one association to CourseBlock
    @OneToMany(mappedBy = "endAcPeriod")
    private List<CourseBlock> courseBlocks1;

    //bi-directional many-to-one association to CourseBlock
    @OneToMany(mappedBy = "startAcPeriod")
    private List<CourseBlock> courseBlocks2;

    //bi-directional many-to-one association to OfferedCourse
    @OneToMany(mappedBy = "acPeriod")
    private List<OfferedCourse> offeredCourses;

    //bi-directional many-to-one association to RubricCell
    @OneToMany(mappedBy = "acPeriod")
    private List<RubricCell> rubricCells;

    public AcPeriod() {
        //Entity constructor
    }

    public long getAcPeriodId() {
        return this.acPeriodId;
    }

    public void setAcPeriodId(long acPeriodId) {
        this.acPeriodId = acPeriodId;
    }

    public String getAcPeriodNameEng() {
        return this.acPeriodNameEng;
    }

    public void setAcPeriodNameEng(String acPeriodNameEng) {
        this.acPeriodNameEng = acPeriodNameEng;
    }

    public String getAcPeriodNameSpa() {
        return this.acPeriodNameSpa;
    }

    public void setAcPeriodNameSpa(String acPeriodNameSpa) {
        this.acPeriodNameSpa = acPeriodNameSpa;
    }

    public int getAcPeriodNumeric() {
        return this.acPeriodNumeric;
    }

    public void setAcPeriodNumeric(int acPeriodNumeric) {
        this.acPeriodNumeric = acPeriodNumeric;
    }

    public List<AcadProgram> getAcadPrograms1() {
        return this.acadPrograms1;
    }

    public void setAcadPrograms1(List<AcadProgram> acadPrograms1) {
        this.acadPrograms1 = acadPrograms1;
    }

    public AcadProgram addAcadPrograms1(AcadProgram acadPrograms1) {
        getAcadPrograms1().add(acadPrograms1);
        acadPrograms1.setEndAcPeriod(this);

        return acadPrograms1;
    }

    public AcadProgram removeAcadPrograms1(AcadProgram acadPrograms1) {
        getAcadPrograms1().remove(acadPrograms1);
        acadPrograms1.setEndAcPeriod(null);

        return acadPrograms1;
    }

    public List<AcadProgram> getAcadPrograms2() {
        return this.acadPrograms2;
    }

    public void setAcadPrograms2(List<AcadProgram> acadPrograms2) {
        this.acadPrograms2 = acadPrograms2;
    }

    public AcadProgram addAcadPrograms2(AcadProgram acadPrograms2) {
        getAcadPrograms2().add(acadPrograms2);
        acadPrograms2.setStartAcPeriod(this);

        return acadPrograms2;
    }

    public AcadProgram removeAcadPrograms2(AcadProgram acadPrograms2) {
        getAcadPrograms2().remove(acadPrograms2);
        acadPrograms2.setStartAcPeriod(null);

        return acadPrograms2;
    }

    public List<AcadProgCurriculum> getAcadProgCurriculums1() {
        return this.acadProgCurriculums1;
    }

    public void setAcadProgCurriculums1(List<AcadProgCurriculum> acadProgCurriculums1) {
        this.acadProgCurriculums1 = acadProgCurriculums1;
    }

    public AcadProgCurriculum addAcadProgCurriculums1(AcadProgCurriculum acadProgCurriculums1) {
        getAcadProgCurriculums1().add(acadProgCurriculums1);
        acadProgCurriculums1.setStartAcPeriod(this);

        return acadProgCurriculums1;
    }

    public AcadProgCurriculum removeAcadProgCurriculums1(AcadProgCurriculum acadProgCurriculums1) {
        getAcadProgCurriculums1().remove(acadProgCurriculums1);
        acadProgCurriculums1.setStartAcPeriod(null);

        return acadProgCurriculums1;
    }

    public List<AcadProgCurriculum> getAcadProgCurriculums2() {
        return this.acadProgCurriculums2;
    }

    public void setAcadProgCurriculums2(List<AcadProgCurriculum> acadProgCurriculums2) {
        this.acadProgCurriculums2 = acadProgCurriculums2;
    }

    public AcadProgCurriculum addAcadProgCurriculums2(AcadProgCurriculum acadProgCurriculums2) {
        getAcadProgCurriculums2().add(acadProgCurriculums2);
        acadProgCurriculums2.setEndAcPeriod(this);

        return acadProgCurriculums2;
    }

    public AcadProgCurriculum removeAcadProgCurriculums2(AcadProgCurriculum acadProgCurriculums2) {
        getAcadProgCurriculums2().remove(acadProgCurriculums2);
        acadProgCurriculums2.setEndAcPeriod(null);

        return acadProgCurriculums2;
    }

    public List<AssmtGenPlan> getAssmtGenPlans1() {
        return this.assmtGenPlans1;
    }

    public void setAssmtGenPlans1(List<AssmtGenPlan> assmtGenPlans1) {
        this.assmtGenPlans1 = assmtGenPlans1;
    }

    public AssmtGenPlan addAssmtGenPlans1(AssmtGenPlan assmtGenPlans1) {
        getAssmtGenPlans1().add(assmtGenPlans1);
        assmtGenPlans1.setStartAcPeriod(this);

        return assmtGenPlans1;
    }

    public AssmtGenPlan removeAssmtGenPlans1(AssmtGenPlan assmtGenPlans1) {
        getAssmtGenPlans1().remove(assmtGenPlans1);
        assmtGenPlans1.setStartAcPeriod(null);

        return assmtGenPlans1;
    }

    public List<AssmtGenPlan> getAssmtGenPlans2() {
        return this.assmtGenPlans2;
    }

    public void setAssmtGenPlans2(List<AssmtGenPlan> assmtGenPlans2) {
        this.assmtGenPlans2 = assmtGenPlans2;
    }

    public AssmtGenPlan addAssmtGenPlans2(AssmtGenPlan assmtGenPlans2) {
        getAssmtGenPlans2().add(assmtGenPlans2);
        assmtGenPlans2.setEndAcPeriod(this);

        return assmtGenPlans2;
    }

    public AssmtGenPlan removeAssmtGenPlans2(AssmtGenPlan assmtGenPlans2) {
        getAssmtGenPlans2().remove(assmtGenPlans2);
        assmtGenPlans2.setEndAcPeriod(null);

        return assmtGenPlans2;
    }

    public List<AssmtPlanCycle> getAssmtPlanCycles1() {
        return this.assmtPlanCycles1;
    }

    public void setAssmtPlanCycles1(List<AssmtPlanCycle> assmtPlanCycles1) {
        this.assmtPlanCycles1 = assmtPlanCycles1;
    }

    public AssmtPlanCycle addAssmtPlanCycles1(AssmtPlanCycle assmtPlanCycles1) {
        getAssmtPlanCycles1().add(assmtPlanCycles1);
        assmtPlanCycles1.setEndAcPeriod(this);

        return assmtPlanCycles1;
    }

    public AssmtPlanCycle removeAssmtPlanCycles1(AssmtPlanCycle assmtPlanCycles1) {
        getAssmtPlanCycles1().remove(assmtPlanCycles1);
        assmtPlanCycles1.setEndAcPeriod(null);

        return assmtPlanCycles1;
    }

    public List<AssmtPlanCycle> getAssmtPlanCycles2() {
        return this.assmtPlanCycles2;
    }

    public void setAssmtPlanCycles2(List<AssmtPlanCycle> assmtPlanCycles2) {
        this.assmtPlanCycles2 = assmtPlanCycles2;
    }

    public AssmtPlanCycle addAssmtPlanCycles2(AssmtPlanCycle assmtPlanCycles2) {
        getAssmtPlanCycles2().add(assmtPlanCycles2);
        assmtPlanCycles2.setStartAcPeriod(this);

        return assmtPlanCycles2;
    }

    public AssmtPlanCycle removeAssmtPlanCycles2(AssmtPlanCycle assmtPlanCycles2) {
        getAssmtPlanCycles2().remove(assmtPlanCycles2);
        assmtPlanCycles2.setStartAcPeriod(null);

        return assmtPlanCycles2;
    }

    public List<AssmtPlanOut> getAssmtPlanOuts1() {
        return this.assmtPlanOuts1;
    }

    public void setAssmtPlanOuts1(List<AssmtPlanOut> assmtPlanOuts1) {
        this.assmtPlanOuts1 = assmtPlanOuts1;
    }

    public AssmtPlanOut addAssmtPlanOuts1(AssmtPlanOut assmtPlanOuts1) {
        getAssmtPlanOuts1().add(assmtPlanOuts1);
        assmtPlanOuts1.setCollectAcPeriod(this);

        return assmtPlanOuts1;
    }

    public AssmtPlanOut removeAssmtPlanOuts1(AssmtPlanOut assmtPlanOuts1) {
        getAssmtPlanOuts1().remove(assmtPlanOuts1);
        assmtPlanOuts1.setCollectAcPeriod(null);

        return assmtPlanOuts1;
    }

    public List<AssmtPlanOut> getAssmtPlanOuts2() {
        return this.assmtPlanOuts2;
    }

    public void setAssmtPlanOuts2(List<AssmtPlanOut> assmtPlanOuts2) {
        this.assmtPlanOuts2 = assmtPlanOuts2;
    }

    public AssmtPlanOut addAssmtPlanOuts2(AssmtPlanOut assmtPlanOuts2) {
        getAssmtPlanOuts2().add(assmtPlanOuts2);
        assmtPlanOuts2.setAssessAcPeriod(this);

        return assmtPlanOuts2;
    }

    public AssmtPlanOut removeAssmtPlanOuts2(AssmtPlanOut assmtPlanOuts2) {
        getAssmtPlanOuts2().remove(assmtPlanOuts2);
        assmtPlanOuts2.setAssessAcPeriod(null);

        return assmtPlanOuts2;
    }

    public List<AssmtPlanSubcyclev> getAssmtPlanSubcyclevs() {
        return this.assmtPlanSubcyclevs;
    }

    public void setAssmtPlanSubcyclevs(List<AssmtPlanSubcyclev> assmtPlanSubcyclevs) {
        this.assmtPlanSubcyclevs = assmtPlanSubcyclevs;
    }

    public AssmtPlanSubcyclev addAssmtPlanSubcyclev(AssmtPlanSubcyclev assmtPlanSubcyclev) {
        getAssmtPlanSubcyclevs().add(assmtPlanSubcyclev);
        assmtPlanSubcyclev.setAcPeriod(this);

        return assmtPlanSubcyclev;
    }

    public AssmtPlanSubcyclev removeAssmtPlanSubcyclev(AssmtPlanSubcyclev assmtPlanSubcyclev) {
        getAssmtPlanSubcyclevs().remove(assmtPlanSubcyclev);
        assmtPlanSubcyclev.setAcPeriod(null);

        return assmtPlanSubcyclev;
    }

    public List<Course> getCourses1() {
        return this.courses1;
    }

    public void setCourses1(List<Course> courses1) {
        this.courses1 = courses1;
    }

    public Course addCourses1(Course courses1) {
        getCourses1().add(courses1);
        courses1.setEndAcPeriod(this);

        return courses1;
    }

    public Course removeCourses1(Course courses1) {
        getCourses1().remove(courses1);
        courses1.setEndAcPeriod(null);

        return courses1;
    }

    public List<Course> getCourses2() {
        return this.courses2;
    }

    public void setCourses2(List<Course> courses2) {
        this.courses2 = courses2;
    }

    public Course addCourses2(Course courses2) {
        getCourses2().add(courses2);
        courses2.setStartAcPeriod(this);

        return courses2;
    }

    public Course removeCourses2(Course courses2) {
        getCourses2().remove(courses2);
        courses2.setStartAcPeriod(null);

        return courses2;
    }

    public List<CourseBlock> getCourseBlocks1() {
        return this.courseBlocks1;
    }

    public void setCourseBlocks1(List<CourseBlock> courseBlocks1) {
        this.courseBlocks1 = courseBlocks1;
    }

    public CourseBlock addCourseBlocks1(CourseBlock courseBlocks1) {
        getCourseBlocks1().add(courseBlocks1);
        courseBlocks1.setEndAcPeriod(this);

        return courseBlocks1;
    }

    public CourseBlock removeCourseBlocks1(CourseBlock courseBlocks1) {
        getCourseBlocks1().remove(courseBlocks1);
        courseBlocks1.setEndAcPeriod(null);

        return courseBlocks1;
    }

    public List<CourseBlock> getCourseBlocks2() {
        return this.courseBlocks2;
    }

    public void setCourseBlocks2(List<CourseBlock> courseBlocks2) {
        this.courseBlocks2 = courseBlocks2;
    }

    public CourseBlock addCourseBlocks2(CourseBlock courseBlocks2) {
        getCourseBlocks2().add(courseBlocks2);
        courseBlocks2.setStartAcPeriod(this);

        return courseBlocks2;
    }

    public CourseBlock removeCourseBlocks2(CourseBlock courseBlocks2) {
        getCourseBlocks2().remove(courseBlocks2);
        courseBlocks2.setStartAcPeriod(null);

        return courseBlocks2;
    }

    public List<OfferedCourse> getOfferedCourses() {
        return this.offeredCourses;
    }

    public void setOfferedCourses(List<OfferedCourse> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }

    public OfferedCourse addOfferedCours(OfferedCourse offeredCours) {
        getOfferedCourses().add(offeredCours);
        offeredCours.setAcPeriod(this);

        return offeredCours;
    }

    public OfferedCourse removeOfferedCours(OfferedCourse offeredCours) {
        getOfferedCourses().remove(offeredCours);
        offeredCours.setAcPeriod(null);

        return offeredCours;
    }

    public List<RubricCell> getRubricCells() {
        return this.rubricCells;
    }

    public void setRubricCells(List<RubricCell> rubricCells) {
        this.rubricCells = rubricCells;
    }

    public RubricCell addRubricCell(RubricCell rubricCell) {
        getRubricCells().add(rubricCell);
        rubricCell.setAcPeriod(this);

        return rubricCell;
    }

    public RubricCell removeRubricCell(RubricCell rubricCell) {
        getRubricCells().remove(rubricCell);
        rubricCell.setAcPeriod(null);

        return rubricCell;
    }

}