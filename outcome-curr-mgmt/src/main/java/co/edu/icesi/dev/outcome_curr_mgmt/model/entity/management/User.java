package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.audit.Changelog;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.Course;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtGenPlan;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.AssmtPlanPi;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.CurrMap;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa.StudOutcome;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.Faculty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "USERS")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "USERS_USRID_GENERATOR", allocationSize = 1, sequenceName = "USERS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_USRID_GENERATOR")
    @Column(name = "USR_ID")
    private long usrId;

    @Column(name="USR_EMAIL")
    private String usrEmail;

    @Column(name="USR_IS_ACTIVE")
    private char usrIsActive;

    @Column(name="USR_NAME")
    private String usrName;

    //bi-directional many-to-one association to AssmtPlanPi
    @OneToMany(mappedBy="user")
    private List<AssmtPlanPi> assmtPlanPis;

    //bi-directional many-to-one association to Changelog
    @OneToMany(mappedBy="user")
    private List<Changelog> changelogs;

    //bi-directional many-to-one association to Course
    @OneToMany(mappedBy="user")
    private List<Course> courses;

    //bi-directional many-to-one association to CurrMap
    @OneToMany(mappedBy="user1")
    private List<CurrMap> currMaps1;

    //bi-directional many-to-one association to CurrMap
    @OneToMany(mappedBy="user2")
    private List<CurrMap> currMaps2;

    //bi-directional many-to-one association to ReadingStatus
    @OneToMany(mappedBy="user")
    private List<ReadingStatus> readingStatuses;

    //bi-directional many-to-many association to AcadProgram
    @ManyToMany
    @JoinTable(
            name="USR_PRG"
            , joinColumns={
            @JoinColumn(name="USR_USR_ID")
    }
            , inverseJoinColumns={
            @JoinColumn(name="ACADP_ACP_ID")
    }
    )
    private List<AcadProgram> acadPrograms;

    //bi-directional many-to-many association to AssmtGenPlan
    @ManyToMany
    @JoinTable(
            name="USR_ASSMT_GEN"
            , joinColumns={
            @JoinColumn(name="USR_USR_ID")
    }
            , inverseJoinColumns={
            @JoinColumn(name="AGENP_ASGPLA_ID")
    }
    )
    private List<AssmtGenPlan> assmtGenPlans;

    //bi-directional many-to-many association to Faculty
    @ManyToMany
    @JoinTable(
            name="USR_FAC"
            , joinColumns={
            @JoinColumn(name="USR_USR_ID")
    }
            , inverseJoinColumns={
            @JoinColumn(name="FAC_FAC_ID")
    }
    )
    private List<Faculty> faculties;

    //bi-directional many-to-many association to StudOutcome
    @ManyToMany
    @JoinTable(
            name="USR_OUTCOME"
            , joinColumns={
            @JoinColumn(name="USR_USR_ID")
    }
            , inverseJoinColumns={
            @JoinColumn(name="SO_SO_ID")
    }
    )
    private List<StudOutcome> studOutcomes;

    //bi-directional many-to-one association to UsrAssmtGen
    @OneToMany(mappedBy="user")
    private List<UsrAssmtGen> usrAssmtGens;

    //bi-directional many-to-one association to UsrBlock
    @OneToMany(mappedBy="user")
    private List<UsrBlock> usrBlocks;

    //bi-directional many-to-one association to UsrFac
    @OneToMany(mappedBy="user")
    private List<UsrFac> usrFacs;

    //bi-directional many-to-one association to UsrOffcourse
    @OneToMany(mappedBy="user")
    private List<UsrOffcourse> usrOffcourses;

    //bi-directional many-to-one association to UsrOutcome
    @OneToMany(mappedBy="user")
    private List<UsrOutcome> usrOutcomes;

    //bi-directional many-to-one association to UsrPrg
    @OneToMany(mappedBy="user")
    private List<UsrPrg> usrPrgs;

    public User() {
        //Entity constructor
    }

    public long getUsrId() {
        return this.usrId;
    }

    public void setUsrId(long usrId) {
        this.usrId = usrId;
    }

    public String getUsrEmail() {
        return this.usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public char getUsrIsActive() {
        return this.usrIsActive;
    }

    public void setUsrIsActive(char usrIsActive) {
        this.usrIsActive = usrIsActive;
    }

    public String getUsrName() {
        return this.usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public List<AssmtPlanPi> getAssmtPlanPis() {
        return this.assmtPlanPis;
    }

    public void setAssmtPlanPis(List<AssmtPlanPi> assmtPlanPis) {
        this.assmtPlanPis = assmtPlanPis;
    }

    public AssmtPlanPi addAssmtPlanPi(AssmtPlanPi assmtPlanPi) {
        getAssmtPlanPis().add(assmtPlanPi);
        assmtPlanPi.setUser(this);

        return assmtPlanPi;
    }

    public AssmtPlanPi removeAssmtPlanPi(AssmtPlanPi assmtPlanPi) {
        getAssmtPlanPis().remove(assmtPlanPi);
        assmtPlanPi.setUser(null);

        return assmtPlanPi;
    }

    public List<Changelog> getChangelogs() {
        return this.changelogs;
    }

    public void setChangelogs(List<Changelog> changelogs) {
        this.changelogs = changelogs;
    }

    public Changelog addChangelog(Changelog changelog) {
        getChangelogs().add(changelog);
        changelog.setUser(this);

        return changelog;
    }

    public Changelog removeChangelog(Changelog changelog) {
        getChangelogs().remove(changelog);
        changelog.setUser(null);

        return changelog;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course addCours(Course cours) {
        getCourses().add(cours);
        cours.setUser(this);

        return cours;
    }

    public Course removeCours(Course cours) {
        getCourses().remove(cours);
        cours.setUser(null);

        return cours;
    }

    public List<CurrMap> getCurrMaps1() {
        return this.currMaps1;
    }

    public void setCurrMaps1(List<CurrMap> currMaps1) {
        this.currMaps1 = currMaps1;
    }

    public CurrMap addCurrMaps1(CurrMap currMaps1) {
        getCurrMaps1().add(currMaps1);
        currMaps1.setUser1(this);

        return currMaps1;
    }

    public CurrMap removeCurrMaps1(CurrMap currMaps1) {
        getCurrMaps1().remove(currMaps1);
        currMaps1.setUser1(null);

        return currMaps1;
    }

    public List<CurrMap> getCurrMaps2() {
        return this.currMaps2;
    }

    public void setCurrMaps2(List<CurrMap> currMaps2) {
        this.currMaps2 = currMaps2;
    }

    public CurrMap addCurrMaps2(CurrMap currMaps2) {
        getCurrMaps2().add(currMaps2);
        currMaps2.setUser2(this);

        return currMaps2;
    }

    public CurrMap removeCurrMaps2(CurrMap currMaps2) {
        getCurrMaps2().remove(currMaps2);
        currMaps2.setUser2(null);

        return currMaps2;
    }

    public List<ReadingStatus> getReadingStatuses() {
        return this.readingStatuses;
    }

    public void setReadingStatuses(List<ReadingStatus> readingStatuses) {
        this.readingStatuses = readingStatuses;
    }

    public ReadingStatus addReadingStatus(ReadingStatus readingStatus) {
        getReadingStatuses().add(readingStatus);
        readingStatus.setUser(this);

        return readingStatus;
    }

    public ReadingStatus removeReadingStatus(ReadingStatus readingStatus) {
        getReadingStatuses().remove(readingStatus);
        readingStatus.setUser(null);

        return readingStatus;
    }

    public List<AcadProgram> getAcadPrograms() {
        return this.acadPrograms;
    }

    public void setAcadPrograms(List<AcadProgram> acadPrograms) {
        this.acadPrograms = acadPrograms;
    }

    public List<AssmtGenPlan> getAssmtGenPlans() {
        return this.assmtGenPlans;
    }

    public void setAssmtGenPlans(List<AssmtGenPlan> assmtGenPlans) {
        this.assmtGenPlans = assmtGenPlans;
    }

    public List<Faculty> getFaculties() {
        return this.faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public List<StudOutcome> getStudOutcomes() {
        return this.studOutcomes;
    }

    public void setStudOutcomes(List<StudOutcome> studOutcomes) {
        this.studOutcomes = studOutcomes;
    }

    public List<UsrAssmtGen> getUsrAssmtGens() {
        return this.usrAssmtGens;
    }

    public void setUsrAssmtGens(List<UsrAssmtGen> usrAssmtGens) {
        this.usrAssmtGens = usrAssmtGens;
    }

    public UsrAssmtGen addUsrAssmtGen(UsrAssmtGen usrAssmtGen) {
        getUsrAssmtGens().add(usrAssmtGen);
        usrAssmtGen.setUser(this);

        return usrAssmtGen;
    }

    public UsrAssmtGen removeUsrAssmtGen(UsrAssmtGen usrAssmtGen) {
        getUsrAssmtGens().remove(usrAssmtGen);
        usrAssmtGen.setUser(null);

        return usrAssmtGen;
    }

    public List<UsrBlock> getUsrBlocks() {
        return this.usrBlocks;
    }

    public void setUsrBlocks(List<UsrBlock> usrBlocks) {
        this.usrBlocks = usrBlocks;
    }

    public UsrBlock addUsrBlock(UsrBlock usrBlock) {
        getUsrBlocks().add(usrBlock);
        usrBlock.setUser(this);

        return usrBlock;
    }

    public UsrBlock removeUsrBlock(UsrBlock usrBlock) {
        getUsrBlocks().remove(usrBlock);
        usrBlock.setUser(null);

        return usrBlock;
    }

    public List<UsrFac> getUsrFacs() {
        return this.usrFacs;
    }

    public void setUsrFacs(List<UsrFac> usrFacs) {
        this.usrFacs = usrFacs;
    }

    public UsrFac addUsrFac(UsrFac usrFac) {
        getUsrFacs().add(usrFac);
        usrFac.setUser(this);

        return usrFac;
    }

    public UsrFac removeUsrFac(UsrFac usrFac) {
        getUsrFacs().remove(usrFac);
        usrFac.setUser(null);

        return usrFac;
    }

    public List<UsrOffcourse> getUsrOffcourses() {
        return this.usrOffcourses;
    }

    public void setUsrOffcourses(List<UsrOffcourse> usrOffcourses) {
        this.usrOffcourses = usrOffcourses;
    }

    public UsrOffcourse addUsrOffcours(UsrOffcourse usrOffcours) {
        getUsrOffcourses().add(usrOffcours);
        usrOffcours.setUser(this);

        return usrOffcours;
    }

    public UsrOffcourse removeUsrOffcours(UsrOffcourse usrOffcours) {
        getUsrOffcourses().remove(usrOffcours);
        usrOffcours.setUser(null);

        return usrOffcours;
    }

    public List<UsrOutcome> getUsrOutcomes() {
        return this.usrOutcomes;
    }

    public void setUsrOutcomes(List<UsrOutcome> usrOutcomes) {
        this.usrOutcomes = usrOutcomes;
    }

    public UsrOutcome addUsrOutcome(UsrOutcome usrOutcome) {
        getUsrOutcomes().add(usrOutcome);
        usrOutcome.setUser(this);

        return usrOutcome;
    }

    public UsrOutcome removeUsrOutcome(UsrOutcome usrOutcome) {
        getUsrOutcomes().remove(usrOutcome);
        usrOutcome.setUser(null);

        return usrOutcome;
    }

    public List<UsrPrg> getUsrPrgs() {
        return this.usrPrgs;
    }

    public void setUsrPrgs(List<UsrPrg> usrPrgs) {
        this.usrPrgs = usrPrgs;
    }

    public UsrPrg addUsrPrg(UsrPrg usrPrg) {
        getUsrPrgs().add(usrPrg);
        usrPrg.setUser(this);

        return usrPrg;
    }

    public UsrPrg removeUsrPrg(UsrPrg usrPrg) {
        getUsrPrgs().remove(usrPrg);
        usrPrg.setUser(null);

        return usrPrg;
    }

}