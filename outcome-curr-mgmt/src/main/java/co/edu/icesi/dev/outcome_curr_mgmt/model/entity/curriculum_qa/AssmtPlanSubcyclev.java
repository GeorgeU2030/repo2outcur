package co.edu.icesi.dev.outcome_curr_mgmt.model.entity.curriculum_qa;

import co.edu.icesi.dev.outcome_curr_mgmt.config.DataModelerGenerated;
import co.edu.icesi.dev.outcome_curr_mgmt.model.entity.management.AcPeriod;
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
@Table(name = "ASSMT_PLAN_SUBCYCLEV")
@NamedQuery(name = "AssmtPlanSubcyclev.findAll", query = "SELECT a FROM AssmtPlanSubcyclev a")
public class AssmtPlanSubcyclev implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ASSMT_PLAN_SUBCYCLEV_ASGPLASUBCYCLEID_GENERATOR", allocationSize = 1, sequenceName = "ASSMT_PLAN_SUBCYCLEV_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSMT_PLAN_SUBCYCLEV_ASGPLASUBCYCLEID_GENERATOR")
    @Column(name = "ASGPLASUBCYCLE_ID")
    private long asgplasubcycleId;

    //bi-directional many-to-one association to AssmtPlanOut
    @OneToMany(mappedBy = "assmtPlanSubcyclev")
    private List<AssmtPlanOut> assmtPlanOuts;

    //bi-directional many-to-one association to AcPeriod
    @ManyToOne
    @JoinColumn(name = "AP_AC_PERIOD_ID")
    private AcPeriod acPeriod;

    //bi-directional many-to-one association to AssmtPlanCycle
    @ManyToOne
    @JoinColumn(name = "APLANC_ASGPLACYCLE_ID")
    private AssmtPlanCycle assmtPlanCycle;

    public AssmtPlanSubcyclev() {
        //Entity constructor
    }

    public long getAsgplasubcycleId() {
        return this.asgplasubcycleId;
    }

    public void setAsgplasubcycleId(long asgplasubcycleId) {
        this.asgplasubcycleId = asgplasubcycleId;
    }

    public List<AssmtPlanOut> getAssmtPlanOuts() {
        return this.assmtPlanOuts;
    }

    public void setAssmtPlanOuts(List<AssmtPlanOut> assmtPlanOuts) {
        this.assmtPlanOuts = assmtPlanOuts;
    }

    public AssmtPlanOut addAssmtPlanOut(AssmtPlanOut assmtPlanOut) {
        getAssmtPlanOuts().add(assmtPlanOut);
        assmtPlanOut.setAssmtPlanSubcyclev(this);

        return assmtPlanOut;
    }

    public AssmtPlanOut removeAssmtPlanOut(AssmtPlanOut assmtPlanOut) {
        getAssmtPlanOuts().remove(assmtPlanOut);
        assmtPlanOut.setAssmtPlanSubcyclev(null);

        return assmtPlanOut;
    }

    public AcPeriod getAcPeriod() {
        return this.acPeriod;
    }

    public void setAcPeriod(AcPeriod acPeriod) {
        this.acPeriod = acPeriod;
    }

    public AssmtPlanCycle getAssmtPlanCycle() {
        return this.assmtPlanCycle;
    }

    public void setAssmtPlanCycle(AssmtPlanCycle assmtPlanCycle) {
        this.assmtPlanCycle = assmtPlanCycle;
    }
}