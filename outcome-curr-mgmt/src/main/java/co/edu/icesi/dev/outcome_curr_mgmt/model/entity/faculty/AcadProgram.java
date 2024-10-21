package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.AcadProgCurriculum;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.AcpCourseblock;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrPrg;
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
@Table(name = "ACAD_PROGRAM")
@NamedQuery(name = "AcadProgram.findAll", query = "SELECT a FROM AcadProgram a")
public class AcadProgram implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ACAD_PROGRAM_ACPID_GENERATOR", allocationSize = 1, sequenceName = "ACAD_PROGRAM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAD_PROGRAM_ACPID_GENERATOR")
    @Column(name = "ACP_ID")
    private long acpId;

    @Column(name = "ACP_IS_ACTIVE")
    private char acpIsActive;

    @Column(name = "ACP_PROG_DESC_ENG")
    private String acpProgDescEng;

    @Column(name = "ACP_PROG_DESC_SPA")
    private String acpProgDescSpa;

    @Column(name = "ACP_PROG_NAME_ENG")
    private String acpProgNameEng;

    @Column(name = "ACP_PROG_NAME_SPA")
    private String acpProgNameSpa;

    @Column(name = "ACP_SNIES")
    private String acpSnies;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "END_AC_PERIOD_ID")
    private AcPeriod endAcPeriod;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "START_AC_PERIOD_ID")
    private AcPeriod startAcPeriod;

    //bi-directional many-to-one association to Faculty
    @ManyToOne
    @JoinColumn(name = "FAC_FAC_ID")
    private Faculty faculty;

    //bi-directional many-to-one association to AcadProgCurriculum
    @OneToMany(mappedBy = "acadProgram")
    private List<AcadProgCurriculum> acadProgCurriculums;

    //bi-directional many-to-one association to AcpCourseblock
    @OneToMany(mappedBy = "acadProgram")
    private List<AcpCourseblock> acpCourseblocks;

    //bi-directional many-to-one association to AssessmentType
    @OneToMany(mappedBy = "acadProgram")
    private List<AssessmentType> assessmentTypes;

    //bi-directional many-to-one association to AssmtGenPlan
    @OneToMany(mappedBy = "acadProgram")
    private List<AssmtGenPlan> assmtGenPlans;

    //bi-directional many-to-one association to PerfLvl
    @OneToMany(mappedBy = "acadProgram")
    private List<PerfLvl> perfLvls;

    //bi-directional many-to-one association to PiLvlCateg
    @OneToMany(mappedBy = "acadProgram")
    private List<PiLvlCateg> piLvlCategs;

    //bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "acadPrograms")
    private List<User> users;

    //bi-directional many-to-one association to UsrPrg
    @OneToMany(mappedBy = "acadProgram")
    private List<UsrPrg> usrPrgs;

    public AcadProgram() {
        //Entity constructor
    }

    public long getAcpId() {
        return this.acpId;
    }

    public void setAcpId(long acpId) {
        this.acpId = acpId;
    }

    public char getAcpIsActive() {
        return this.acpIsActive;
    }

    public void setAcpIsActive(char acpIsActive) {
        this.acpIsActive = acpIsActive;
    }

    public String getAcpProgDescEng() {
        return this.acpProgDescEng;
    }

    public void setAcpProgDescEng(String acpProgDescEng) {
        this.acpProgDescEng = acpProgDescEng;
    }

    public String getAcpProgDescSpa() {
        return this.acpProgDescSpa;
    }

    public void setAcpProgDescSpa(String acpProgDescSpa) {
        this.acpProgDescSpa = acpProgDescSpa;
    }

    public String getAcpProgNameEng() {
        return this.acpProgNameEng;
    }

    public void setAcpProgNameEng(String acpProgNameEng) {
        this.acpProgNameEng = acpProgNameEng;
    }

    public String getAcpProgNameSpa() {
        return this.acpProgNameSpa;
    }

    public void setAcpProgNameSpa(String acpProgNameSpa) {
        this.acpProgNameSpa = acpProgNameSpa;
    }

    public String getAcpSnies() {
        return this.acpSnies;
    }

    public void setAcpSnies(String acpSnies) {
        this.acpSnies = acpSnies;
    }

    public AcPeriod getEndAcPeriod() {
        return this.endAcPeriod;
    }

    public void setEndAcPeriod(AcPeriod acPeriod1) {
        this.endAcPeriod = acPeriod1;
    }

    public AcPeriod getStartAcPeriod() {
        return this.startAcPeriod;
    }

    public void setStartAcPeriod(AcPeriod acPeriod2) {
        this.startAcPeriod = acPeriod2;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<AcadProgCurriculum> getAcadProgCurriculums() {
        return this.acadProgCurriculums;
    }

    public void setAcadProgCurriculums(List<AcadProgCurriculum> acadProgCurriculums) {
        this.acadProgCurriculums = acadProgCurriculums;
    }

    public AcadProgCurriculum addAcadProgCurriculum(AcadProgCurriculum acadProgCurriculum) {
        getAcadProgCurriculums().add(acadProgCurriculum);
        acadProgCurriculum.setAcadProgram(this);

        return acadProgCurriculum;
    }

    public AcadProgCurriculum removeAcadProgCurriculum(AcadProgCurriculum acadProgCurriculum) {
        getAcadProgCurriculums().remove(acadProgCurriculum);
        acadProgCurriculum.setAcadProgram(null);

        return acadProgCurriculum;
    }

    public List<AcpCourseblock> getAcpCourseblocks() {
        return this.acpCourseblocks;
    }

    public void setAcpCourseblocks(List<AcpCourseblock> acpCourseblocks) {
        this.acpCourseblocks = acpCourseblocks;
    }

    public AcpCourseblock addAcpCourseblock(AcpCourseblock acpCourseblock) {
        getAcpCourseblocks().add(acpCourseblock);
        acpCourseblock.setAcadProgram(this);

        return acpCourseblock;
    }

    public AcpCourseblock removeAcpCourseblock(AcpCourseblock acpCourseblock) {
        getAcpCourseblocks().remove(acpCourseblock);
        acpCourseblock.setAcadProgram(null);

        return acpCourseblock;
    }

    public List<AssessmentType> getAssessmentTypes() {
        return this.assessmentTypes;
    }

    public void setAssessmentTypes(List<AssessmentType> assessmentTypes) {
        this.assessmentTypes = assessmentTypes;
    }

    public AssessmentType addAssessmentType(AssessmentType assessmentType) {
        getAssessmentTypes().add(assessmentType);
        assessmentType.setAcadProgram(this);

        return assessmentType;
    }

    public AssessmentType removeAssessmentType(AssessmentType assessmentType) {
        getAssessmentTypes().remove(assessmentType);
        assessmentType.setAcadProgram(null);

        return assessmentType;
    }

    public List<AssmtGenPlan> getAssmtGenPlans() {
        return this.assmtGenPlans;
    }

    public void setAssmtGenPlans(List<AssmtGenPlan> assmtGenPlans) {
        this.assmtGenPlans = assmtGenPlans;
    }

    public AssmtGenPlan addAssmtGenPlan(AssmtGenPlan assmtGenPlan) {
        getAssmtGenPlans().add(assmtGenPlan);
        assmtGenPlan.setAcadProgram(this);

        return assmtGenPlan;
    }

    public AssmtGenPlan removeAssmtGenPlan(AssmtGenPlan assmtGenPlan) {
        getAssmtGenPlans().remove(assmtGenPlan);
        assmtGenPlan.setAcadProgram(null);

        return assmtGenPlan;
    }

    public List<PerfLvl> getPerfLvls() {
        return this.perfLvls;
    }

    public void setPerfLvls(List<PerfLvl> perfLvls) {
        this.perfLvls = perfLvls;
    }

    public PerfLvl addPerfLvl(PerfLvl perfLvl) {
        getPerfLvls().add(perfLvl);
        perfLvl.setAcadProgram(this);

        return perfLvl;
    }

    public PerfLvl removePerfLvl(PerfLvl perfLvl) {
        getPerfLvls().remove(perfLvl);
        perfLvl.setAcadProgram(null);

        return perfLvl;
    }

    public List<PiLvlCateg> getPiLvlCategs() {
        return this.piLvlCategs;
    }

    public void setPiLvlCategs(List<PiLvlCateg> piLvlCategs) {
        this.piLvlCategs = piLvlCategs;
    }

    public PiLvlCateg addPiLvlCateg(PiLvlCateg piLvlCateg) {
        getPiLvlCategs().add(piLvlCateg);
        piLvlCateg.setAcadProgram(this);

        return piLvlCateg;
    }

    public PiLvlCateg removePiLvlCateg(PiLvlCateg piLvlCateg) {
        getPiLvlCategs().remove(piLvlCateg);
        piLvlCateg.setAcadProgram(null);

        return piLvlCateg;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<UsrPrg> getUsrPrgs() {
        return this.usrPrgs;
    }

    public void setUsrPrgs(List<UsrPrg> usrPrgs) {
        this.usrPrgs = usrPrgs;
    }

    public UsrPrg addUsrPrg(UsrPrg usrPrg) {
        getUsrPrgs().add(usrPrg);
        usrPrg.setAcadProgram(this);

        return usrPrg;
    }

    public UsrPrg removeUsrPrg(UsrPrg usrPrg) {
        getUsrPrgs().remove(usrPrg);
        usrPrg.setAcadProgram(null);

        return usrPrg;
    }

}