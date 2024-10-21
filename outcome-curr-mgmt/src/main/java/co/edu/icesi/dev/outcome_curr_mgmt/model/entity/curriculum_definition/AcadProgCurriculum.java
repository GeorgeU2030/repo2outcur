package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.CurrMap;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name = "ACAD_PROG_CURRICULUM")
@NamedQuery(name = "AcadProgCurriculum.findAll", query = "SELECT a FROM AcadProgCurriculum a")
public class AcadProgCurriculum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ACAD_PROG_CURRICULUM_APCID_GENERATOR", allocationSize = 1, sequenceName = "ACAD_PROG_CURRICULUM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAD_PROG_CURRICULUM_APCID_GENERATOR")
    @Column(name = "APC_ID")
    private long apcId;

    @Column(name = "APC_NAME_ENG")
    private String apcNameEng;

    @Column(name = "APC_NAME_SPA")
    private String apcNameSpa;

    //bi-directional many-to-one association to AcadProgram
    @ManyToOne
    @JoinColumn(name = "ACADP_ACP_ID")
    private AcadProgram acadProgram;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "START_PERIOD")
    private AcPeriod startAcPeriod;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "END_PERIOD")
    private AcPeriod endAcPeriod;

    //bi-directional many-to-one association to CbAcadpcur
    @OneToMany(mappedBy = "acadProgCurriculum")
    private List<CbAcadpcur> cbAcadpcurs;

    //bi-directional many-to-many association to Course
    @ManyToMany(mappedBy = "acadProgCurriculums")
    private List<Course> courses;

    //bi-directional many-to-one association to CourseCurr
    @OneToMany(mappedBy = "acadProgCurriculum")
    private List<CourseCurr> courseCurrs;

    //bi-directional many-to-one association to CurrMap
    @OneToMany(mappedBy = "acadProgCurriculum")
    private List<CurrMap> currMaps;

    //bi-directional many-to-many association to StudOutcome
    @ManyToMany(mappedBy = "acadProgCurriculums")
    private List<StudOutcome> studOutcomes;

    public AcadProgCurriculum() {
        //Entity constructor
    }

    public long getApcId() {
        return this.apcId;
    }

    public void setApcId(long apcId) {
        this.apcId = apcId;
    }

    public String getApcNameEng() {
        return this.apcNameEng;
    }

    public void setApcNameEng(String apcNameEng) {
        this.apcNameEng = apcNameEng;
    }

    public String getApcNameSpa() {
        return this.apcNameSpa;
    }

    public void setApcNameSpa(String apcNameSpa) {
        this.apcNameSpa = apcNameSpa;
    }

    public AcadProgram getAcadProgram() {
        return this.acadProgram;
    }

    public void setAcadProgram(AcadProgram acadProgram) {
        this.acadProgram = acadProgram;
    }

    public AcPeriod getStartAcPeriod() {
        return this.startAcPeriod;
    }

    public void setStartAcPeriod(AcPeriod acPeriod1) {
        this.startAcPeriod = acPeriod1;
    }

    public AcPeriod getEndAcPeriod() {
        return this.endAcPeriod;
    }

    public void setEndAcPeriod(AcPeriod acPeriod2) {
        this.endAcPeriod = acPeriod2;
    }

    public List<CbAcadpcur> getCbAcadpcurs() {
        return this.cbAcadpcurs;
    }

    public void setCbAcadpcurs(List<CbAcadpcur> cbAcadpcurs) {
        this.cbAcadpcurs = cbAcadpcurs;
    }

    public CbAcadpcur addCbAcadpcur(CbAcadpcur cbAcadpcur) {
        getCbAcadpcurs().add(cbAcadpcur);
        cbAcadpcur.setAcadProgCurriculum(this);

        return cbAcadpcur;
    }

    public CbAcadpcur removeCbAcadpcur(CbAcadpcur cbAcadpcur) {
        getCbAcadpcurs().remove(cbAcadpcur);
        cbAcadpcur.setAcadProgCurriculum(null);

        return cbAcadpcur;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<CourseCurr> getCourseCurrs() {
        return this.courseCurrs;
    }

    public void setCourseCurrs(List<CourseCurr> courseCurrs) {
        this.courseCurrs = courseCurrs;
    }

    public CourseCurr addCourseCurr(CourseCurr courseCurr) {
        getCourseCurrs().add(courseCurr);
        courseCurr.setAcadProgCurriculum(this);

        return courseCurr;
    }

    public CourseCurr removeCourseCurr(CourseCurr courseCurr) {
        getCourseCurrs().remove(courseCurr);
        courseCurr.setAcadProgCurriculum(null);

        return courseCurr;
    }

    public List<CurrMap> getCurrMaps() {
        return this.currMaps;
    }

    public void setCurrMaps(List<CurrMap> currMaps) {
        this.currMaps = currMaps;
    }

    public CurrMap addCurrMap(CurrMap currMap) {
        getCurrMaps().add(currMap);
        currMap.setAcadProgCurriculum(this);

        return currMap;
    }

    public CurrMap removeCurrMap(CurrMap currMap) {
        getCurrMaps().remove(currMap);
        currMap.setAcadProgCurriculum(null);

        return currMap;
    }

    public List<StudOutcome> getStudOutcomes() {
        return this.studOutcomes;
    }

    public void setStudOutcomes(List<StudOutcome> studOutcomes) {
        this.studOutcomes = studOutcomes;
    }

}