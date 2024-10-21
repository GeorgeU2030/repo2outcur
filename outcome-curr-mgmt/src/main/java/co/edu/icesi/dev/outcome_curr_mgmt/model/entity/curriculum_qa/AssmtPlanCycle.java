package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
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
@Table(name = "ASSMT_PLAN_CYCLE")
@NamedQuery(name = "AssmtPlanCycle.findAll", query = "SELECT a FROM AssmtPlanCycle a")
public class AssmtPlanCycle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ASSMT_PLAN_CYCLE_ASGPLACYCLEID_GENERATOR", allocationSize = 1, sequenceName = "ASSMT_PLAN_CYCLE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSMT_PLAN_CYCLE_ASGPLACYCLEID_GENERATOR")
    @Column(name = "ASGPLACYCLE_ID")
    private long asgplacycleId;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "END_AC_PERIOD_ID")
    private AcPeriod endAcPeriod;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "START_AC_PERIOD_ID")
    private AcPeriod startAcPeriod;

    //bi-directional many-to-one association to AssmtGenPlan
    @ManyToOne
    @JoinColumn(name = "AGENP_ASGPLA_ID")
    private AssmtGenPlan assmtGenPlan;

    //bi-directional many-to-one association to AssmtPlanSubcyclev
    @OneToMany(mappedBy = "assmtPlanCycle", fetch = FetchType.EAGER)
    private List<AssmtPlanSubcyclev> assmtPlanSubcyclevs;

    public AssmtPlanCycle() {
        //Entity constructor
    }

    public long getAsgplacycleId() {
        return this.asgplacycleId;
    }

    public void setAsgplacycleId(long asgplacycleId) {
        this.asgplacycleId = asgplacycleId;
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

    public AssmtGenPlan getAssmtGenPlan() {
        return this.assmtGenPlan;
    }

    public void setAssmtGenPlan(AssmtGenPlan assmtGenPlan) {
        this.assmtGenPlan = assmtGenPlan;
    }

    public List<AssmtPlanSubcyclev> getAssmtPlanSubcyclevs() {
        return this.assmtPlanSubcyclevs;
    }

    public void setAssmtPlanSubcyclevs(List<AssmtPlanSubcyclev> assmtPlanSubcyclevs) {
        this.assmtPlanSubcyclevs = assmtPlanSubcyclevs;
    }

    public AssmtPlanSubcyclev addAssmtPlanSubcyclev(AssmtPlanSubcyclev assmtPlanSubcyclev) {
        getAssmtPlanSubcyclevs().add(assmtPlanSubcyclev);
        assmtPlanSubcyclev.setAssmtPlanCycle(this);

        return assmtPlanSubcyclev;
    }

    public AssmtPlanSubcyclev removeAssmtPlanSubcyclev(AssmtPlanSubcyclev assmtPlanSubcyclev) {
        getAssmtPlanSubcyclevs().remove(assmtPlanSubcyclev);
        assmtPlanSubcyclev.setAssmtPlanCycle(null);

        return assmtPlanSubcyclev;
    }

}