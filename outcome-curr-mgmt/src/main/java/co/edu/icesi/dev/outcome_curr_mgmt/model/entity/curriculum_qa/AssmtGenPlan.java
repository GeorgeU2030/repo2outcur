package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.faculty.AcadProgram;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.UsrAssmtGen;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "ASSMT_GEN_PLAN")
@NamedQuery(name = "AssmtGenPlan.findAll", query = "SELECT a FROM AssmtGenPlan a")
public class AssmtGenPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ASSMT_GEN_PLAN_ASGPLAID_GENERATOR", allocationSize = 1, sequenceName = "ASSMT_GEN_PLAN_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSMT_GEN_PLAN_ASGPLAID_GENERATOR")
    @Column(name = "ASGPLA_ID")
    private long asgplaId;

    @Column(name = "ASGPLA_STATUS")
    private String asgplaStatus;

    //bi-directional many-to-one association to AcadProgram
    @ManyToOne
    @JoinColumn(name = "ACADP_ACP_ID")
    private AcadProgram acadProgram;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "START_AC_PERIOD_ID")
    private AcPeriod startAcPeriod;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "END_AC_PERIOD_ID")
    private AcPeriod endAcPeriod;

    //bi-directional many-to-one association to AssmtPlanCycle
    @OneToMany(mappedBy = "assmtGenPlan", fetch = FetchType.EAGER)
    private List<AssmtPlanCycle> assmtPlanCycles;

    //bi-directional many-to-one association to ImprovementPlan
    @OneToMany(mappedBy = "assmtGenPlan")
    private List<ImprovementPlan> improvementPlans;

    //bi-directional many-to-one association to StudOutcome
    @OneToMany(mappedBy = "assmtGenPlan")
    private List<StudOutcome> studOutcomes;

    //bi-directional many-to-one association to UsrAssmtGen
    @OneToMany(mappedBy = "assmtGenPlan")
    private List<UsrAssmtGen> usrAssmtGens;

    public AssmtGenPlan() {
        //Entity constructor
    }

    public long getAsgplaId() {
        return this.asgplaId;
    }

    public void setAsgplaId(long asgplaId) {
        this.asgplaId = asgplaId;
    }

    public String getAsgplaStatus() {
        return this.asgplaStatus;
    }

    public void setAsgplaStatus(String asgplaStatus) {
        this.asgplaStatus = asgplaStatus;
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

    public List<AssmtPlanCycle> getAssmtPlanCycles() {
        return this.assmtPlanCycles;
    }

    public void setAssmtPlanCycles(List<AssmtPlanCycle> assmtPlanCycles) {
        this.assmtPlanCycles = assmtPlanCycles;
    }

    public AssmtPlanCycle addAssmtPlanCycle(AssmtPlanCycle assmtPlanCycle) {
        getAssmtPlanCycles().add(assmtPlanCycle);
        assmtPlanCycle.setAssmtGenPlan(this);

        return assmtPlanCycle;
    }

    public AssmtPlanCycle removeAssmtPlanCycle(AssmtPlanCycle assmtPlanCycle) {
        getAssmtPlanCycles().remove(assmtPlanCycle);
        assmtPlanCycle.setAssmtGenPlan(null);

        return assmtPlanCycle;
    }

    public List<ImprovementPlan> getImprovementPlans() {
        return this.improvementPlans;
    }

    public void setImprovementPlans(List<ImprovementPlan> improvementPlans) {
        this.improvementPlans = improvementPlans;
    }

    public ImprovementPlan addImprovementPlan(ImprovementPlan improvementPlan) {
        getImprovementPlans().add(improvementPlan);
        improvementPlan.setAssmtGenPlan(this);

        return improvementPlan;
    }

    public ImprovementPlan removeImprovementPlan(ImprovementPlan improvementPlan) {
        getImprovementPlans().remove(improvementPlan);
        improvementPlan.setAssmtGenPlan(null);

        return improvementPlan;
    }

    public List<StudOutcome> getStudOutcomes() {
        return this.studOutcomes;
    }

    public void setStudOutcomes(List<StudOutcome> studOutcomes) {
        this.studOutcomes = studOutcomes;
    }

    public StudOutcome addStudOutcome(StudOutcome studOutcome) {
        getStudOutcomes().add(studOutcome);
        studOutcome.setAssmtGenPlan(this);

        return studOutcome;
    }

    public StudOutcome removeStudOutcome(StudOutcome studOutcome) {
        getStudOutcomes().remove(studOutcome);
        studOutcome.setAssmtGenPlan(null);

        return studOutcome;
    }

    public List<UsrAssmtGen> getUsrAssmtGens() {
        return this.usrAssmtGens;
    }

    public void setUsrAssmtGens(List<UsrAssmtGen> usrAssmtGens) {
        this.usrAssmtGens = usrAssmtGens;
    }

    public UsrAssmtGen addUsrAssmtGen(UsrAssmtGen usrAssmtGen) {
        getUsrAssmtGens().add(usrAssmtGen);
        usrAssmtGen.setAssmtGenPlan(this);

        return usrAssmtGen;
    }

    public UsrAssmtGen removeUsrAssmtGen(UsrAssmtGen usrAssmtGen) {
        getUsrAssmtGens().remove(usrAssmtGen);
        usrAssmtGen.setAssmtGenPlan(null);

        return usrAssmtGen;
    }

}