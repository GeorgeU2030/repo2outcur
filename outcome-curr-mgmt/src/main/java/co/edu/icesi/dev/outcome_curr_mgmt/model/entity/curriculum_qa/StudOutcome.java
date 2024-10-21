package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_definition.AcadProgCurriculum;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.User;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrOutcome;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "STUD_OUTCOME")
@NamedQuery(name = "StudOutcome.findAll", query = "SELECT s FROM StudOutcome s")
public class StudOutcome implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "STUD_OUTCOME_SOID_GENERATOR", allocationSize = 1, sequenceName = "STUD_OUTCOME_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUD_OUTCOME_SOID_GENERATOR")
    @Column(name = "SO_ID")
    private long soId;

    @Column(name = "SO_ACRONYM")
    private String soAcronym;

    @Column(name = "SO_IS_ACTIVE")
    private char soIsActive;

    @Column(name = "SO_LONG_NAME_ENG", length = 1000)
    private String soLongNameEng;

    @Column(name = "SO_LONG_NAME_SPA", length = 1000)
    private String soLongNameSpa;

    @Column(name = "SO_ORDINAL_NUMBER")
    private int soOrdinalNumber;

    @Column(name = "SO_SHORT_NAME_ENG")
    private String soShortNameEng;

    @Column(name = "SO_SHORT_NAME_SPA")
    private String soShortNameSpa;

    //bi-directional many-to-one association to AssmtPlanOut
    @OneToMany(mappedBy = "studOutcome")
    private List<AssmtPlanOut> assmtPlanOuts;

    //bi-directional many-to-one association to PerfIndicator
    @OneToMany(mappedBy = "studOutcome")
    private List<PerfIndicator> perfIndicators;

    //bi-directional many-to-many association to AcadProgCurriculum
    @ManyToMany
    @JoinTable(
            name = "ACPCU_SO"
            , joinColumns = {
            @JoinColumn(name = "SO_SO_ID")
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "ACADP_CUR_APC_ID")
    }
    )
    private List<AcadProgCurriculum> acadProgCurriculums;

    //bi-directional many-to-one association to AssmtGenPlan
    @ManyToOne
    @JoinColumn(name = "AGENP_ASGPLA_ID")
    private AssmtGenPlan assmtGenPlan;

    //bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "studOutcomes")
    private List<User> users;

    //bi-directional many-to-one association to UsrOutcome
    @OneToMany(mappedBy = "studOutcome")
    private List<UsrOutcome> usrOutcomes;

    public StudOutcome() {
        //Entity constructor
    }

    public long getSoId() {
        return this.soId;
    }

    public void setSoId(long soId) {
        this.soId = soId;
    }

    public String getSoAcronym() {
        return this.soAcronym;
    }

    public void setSoAcronym(String soAcronym) {
        this.soAcronym = soAcronym;
    }

    public char getSoIsActive() {
        return this.soIsActive;
    }

    public void setSoIsActive(char soIsActive) {
        this.soIsActive = soIsActive;
    }

    public String getSoLongNameEng() {
        return this.soLongNameEng;
    }

    public void setSoLongNameEng(String soLongNameEng) {
        this.soLongNameEng = soLongNameEng;
    }

    public String getSoLongNameSpa() {
        return this.soLongNameSpa;
    }

    public void setSoLongNameSpa(String soLongNameSpa) {
        this.soLongNameSpa = soLongNameSpa;
    }

    public int getSoOrdinalNumber() {
        return this.soOrdinalNumber;
    }

    public void setSoOrdinalNumber(int soOrdinalNumber) {
        this.soOrdinalNumber = soOrdinalNumber;
    }

    public String getSoShortNameEng() {
        return this.soShortNameEng;
    }

    public void setSoShortNameEng(String soShortNameEng) {
        this.soShortNameEng = soShortNameEng;
    }

    public String getSoShortNameSpa() {
        return this.soShortNameSpa;
    }

    public void setSoShortNameSpa(String soShortNameSpa) {
        this.soShortNameSpa = soShortNameSpa;
    }

    public List<AssmtPlanOut> getAssmtPlanOuts() {
        return this.assmtPlanOuts;
    }

    public void setAssmtPlanOuts(List<AssmtPlanOut> assmtPlanOuts) {
        this.assmtPlanOuts = assmtPlanOuts;
    }

    public AssmtPlanOut addAssmtPlanOut(AssmtPlanOut assmtPlanOut) {
        getAssmtPlanOuts().add(assmtPlanOut);
        assmtPlanOut.setStudOutcome(this);

        return assmtPlanOut;
    }

    public AssmtPlanOut removeAssmtPlanOut(AssmtPlanOut assmtPlanOut) {
        getAssmtPlanOuts().remove(assmtPlanOut);
        assmtPlanOut.setStudOutcome(null);

        return assmtPlanOut;
    }

    public List<PerfIndicator> getPerfIndicators() {
        return this.perfIndicators;
    }

    public void setPerfIndicators(List<PerfIndicator> perfIndicators) {
        this.perfIndicators = perfIndicators;
    }

    public PerfIndicator addPerfIndicator(PerfIndicator perfIndicator) {
        getPerfIndicators().add(perfIndicator);
        perfIndicator.setStudOutcome(this);

        return perfIndicator;
    }

    public PerfIndicator removePerfIndicator(PerfIndicator perfIndicator) {
        getPerfIndicators().remove(perfIndicator);
        perfIndicator.setStudOutcome(null);

        return perfIndicator;
    }

    public List<AcadProgCurriculum> getAcadProgCurriculums() {
        return this.acadProgCurriculums;
    }

    public void setAcadProgCurriculums(List<AcadProgCurriculum> acadProgCurriculums) {
        this.acadProgCurriculums = acadProgCurriculums;
    }

    public AssmtGenPlan getAssmtGenPlan() {
        return this.assmtGenPlan;
    }

    public void setAssmtGenPlan(AssmtGenPlan assmtGenPlan) {
        this.assmtGenPlan = assmtGenPlan;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<UsrOutcome> getUsrOutcomes() {
        return this.usrOutcomes;
    }

    public void setUsrOutcomes(List<UsrOutcome> usrOutcomes) {
        this.usrOutcomes = usrOutcomes;
    }

    public UsrOutcome addUsrOutcome(UsrOutcome usrOutcome) {
        getUsrOutcomes().add(usrOutcome);
        usrOutcome.setStudOutcome(this);

        return usrOutcome;
    }

    public UsrOutcome removeUsrOutcome(UsrOutcome usrOutcome) {
        getUsrOutcomes().remove(usrOutcome);
        usrOutcome.setStudOutcome(null);

        return usrOutcome;
    }

}